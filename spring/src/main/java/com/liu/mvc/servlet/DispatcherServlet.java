package com.liu.mvc.servlet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.liu.mvc.beans.BeansUtil;
import com.liu.mvc.beans.ModelAndView;
import com.liu.mvc.beans.ServletProperty;
import com.liu.mvc.beans.View;
import com.liu.mvc.context.WebApplicationContext;
import com.liu.mvc.mapping.HandlerMapping;
import com.liu.mvc.mapping.RequestMappingHandlerMapping;
import com.liu.mvc.resolver.HandlerAdapter;
import com.liu.mvc.resolver.HandlerExceptionResolver;
import com.liu.mvc.resolver.LocaleResolver;
import com.liu.mvc.resolver.RequestMappingHandlerAdapter;
import com.liu.mvc.resolver.ResourceBundleViewResolver;
import com.liu.mvc.resolver.ThemeResolver;
import com.liu.mvc.resolver.ViewResolver;
import com.liu.mvc.support.FlashMapManager;
import com.liu.mvc.support.MultipartResolver;
import com.liu.mvc.support.RequestToViewNameTranslator;
import com.liu.mvc.utils.CollectionUtis;
import com.liu.mvc.utils.HttpUtil;
import com.liu.spring.context.ApplicationContext;
import com.liu.spring.util.ClassUtil;

public class DispatcherServlet extends FrameworkServlet {

	private List<ServletProperty> servletPropertys = new ArrayList<>();

	/**
	 * Well-known name for the MultipartResolver object in the bean factory for this
	 * namespace.
	 */
	public static final String MULTIPART_RESOLVER_BEAN_NAME = "multipartResolver";

	/**
	 * Well-known name for the LocaleResolver object in the bean factory for this
	 * namespace.
	 */
	public static final String LOCALE_RESOLVER_BEAN_NAME = "localeResolver";

	/**
	 * Well-known name for the ThemeResolver object in the bean factory for this
	 * namespace.
	 */
	public static final String THEME_RESOLVER_BEAN_NAME = "themeResolver";

	/**
	 * Well-known name for the HandlerMapping object in the bean factory for this
	 * namespace. Only used when "detectAllHandlerMappings" is turned off.
	 * 
	 * @see #setDetectAllHandlerMappings
	 */
	public static final String HANDLER_MAPPING_BEAN_NAME = "handlerMapping";

	/**
	 * Well-known name for the HandlerAdapter object in the bean factory for this
	 * namespace. Only used when "detectAllHandlerAdapters" is turned off.
	 * 
	 * @see #setDetectAllHandlerAdapters
	 */
	public static final String HANDLER_ADAPTER_BEAN_NAME = "handlerAdapter";

	/**
	 * Well-known name for the HandlerExceptionResolver object in the bean factory
	 * for this namespace. Only used when "detectAllHandlerExceptionResolvers" is
	 * turned off.
	 * 
	 * @see #setDetectAllHandlerExceptionResolvers
	 */
	public static final String HANDLER_EXCEPTION_RESOLVER_BEAN_NAME = "handlerExceptionResolver";

	/**
	 * Well-known name for the RequestToViewNameTranslator object in the bean
	 * factory for this namespace.
	 */
	public static final String REQUEST_TO_VIEW_NAME_TRANSLATOR_BEAN_NAME = "viewNameTranslator";

	/**
	 * Well-known name for the ViewResolver object in the bean factory for this
	 * namespace. Only used when "detectAllViewResolvers" is turned off.
	 * 
	 * @see #setDetectAllViewResolvers
	 */
	public static final String VIEW_RESOLVER_BEAN_NAME = "viewResolver";

	/**
	 * Well-known name for the FlashMapManager object in the bean factory for this
	 * namespace.
	 */
	public static final String FLASH_MAP_MANAGER_BEAN_NAME = "flashMapManager";

	/**
	 * Request attribute to hold the current web application context. Otherwise only
	 * the global web app context is obtainable by tags etc.
	 * 
	 * @see org.springframework.web.servlet.support.RequestContextUtils#findWebApplicationContext
	 */
	public static final String WEB_APPLICATION_CONTEXT_ATTRIBUTE = DispatcherServlet.class.getName() + ".CONTEXT";

	/**
	 * Request attribute to hold the current LocaleResolver, retrievable by views.
	 * 
	 * @see org.springframework.web.servlet.support.RequestContextUtils#getLocaleResolver
	 */
	public static final String LOCALE_RESOLVER_ATTRIBUTE = DispatcherServlet.class.getName() + ".LOCALE_RESOLVER";

	/**
	 * Request attribute to hold the current ThemeResolver, retrievable by views.
	 * 
	 * @see org.springframework.web.servlet.support.RequestContextUtils#getThemeResolver
	 */
	public static final String THEME_RESOLVER_ATTRIBUTE = DispatcherServlet.class.getName() + ".THEME_RESOLVER";

	/**
	 * Request attribute to hold the current ThemeSource, retrievable by views.
	 * 
	 * @see org.springframework.web.servlet.support.RequestContextUtils#getThemeSource
	 */
	public static final String THEME_SOURCE_ATTRIBUTE = DispatcherServlet.class.getName() + ".THEME_SOURCE";

	/**
	 * Name of request attribute that holds a read-only {@code Map<String,?>} with
	 * "input" flash attributes saved by a previous request, if any.
	 * 
	 * @see org.springframework.web.servlet.support.RequestContextUtils#getInputFlashMap(HttpServletRequest)
	 */
	public static final String INPUT_FLASH_MAP_ATTRIBUTE = DispatcherServlet.class.getName() + ".INPUT_FLASH_MAP";

	/**
	 * Name of request attribute that holds the "output" {@link FlashMap} with
	 * attributes to save for a subsequent request.
	 * 
	 * @see org.springframework.web.servlet.support.RequestContextUtils#getOutputFlashMap(HttpServletRequest)
	 */
	public static final String OUTPUT_FLASH_MAP_ATTRIBUTE = DispatcherServlet.class.getName() + ".OUTPUT_FLASH_MAP";

	/**
	 * Name of request attribute that holds the {@link FlashMapManager}.
	 * 
	 * @see org.springframework.web.servlet.support.RequestContextUtils#getFlashMapManager(HttpServletRequest)
	 */
	public static final String FLASH_MAP_MANAGER_ATTRIBUTE = DispatcherServlet.class.getName() + ".FLASH_MAP_MANAGER";

	/**
	 * Name of request attribute that exposes an Exception resolved with an
	 * {@link HandlerExceptionResolver} but where no view was rendered (e.g. setting
	 * the status code).
	 */
	public static final String EXCEPTION_ATTRIBUTE = DispatcherServlet.class.getName() + ".EXCEPTION";

	/** Log category to use when no mapped handler is found for a request. */
	public static final String PAGE_NOT_FOUND_LOG_CATEGORY = "org.springframework.web.servlet.PageNotFound";

	/**
	 * Name of the class path resource (relative to the DispatcherServlet class)
	 * that defines DispatcherServlet's default strategy names.
	 */
	private static final String DEFAULT_STRATEGIES_PATH = "DispatcherServlet.properties";

	/**
	 * Common prefix that DispatcherServlet's default strategy attributes start
	 * with.
	 */
	private static final String DEFAULT_STRATEGIES_PREFIX = "org.springframework.web.servlet";

	/** Detect all HandlerMappings or just expect "handlerMapping" bean? */
	private boolean detectAllHandlerMappings = true;

	/** Detect all HandlerAdapters or just expect "handlerAdapter" bean? */
	private boolean detectAllHandlerAdapters = true;

	/**
	 * Detect all HandlerExceptionResolvers or just expect
	 * "handlerExceptionResolver" bean?
	 */
	private boolean detectAllHandlerExceptionResolvers = true;

	/** Detect all ViewResolvers or just expect "viewResolver" bean? */
	private boolean detectAllViewResolvers = true;

	/**
	 * Throw a NoHandlerFoundException if no Handler was found to process this
	 * request?
	 **/
	private boolean throwExceptionIfNoHandlerFound = false;

	/** Perform cleanup of request attributes after include request? */
	private boolean cleanupAfterInclude = true;

	/** MultipartResolver used by this servlet */

	private MultipartResolver multipartResolver;

	/** LocaleResolver used by this servlet */

	private LocaleResolver localeResolver;

	/** 该servlet使用的MeSeLever */

	private ThemeResolver themeResolver;

	/** 此servlet使用的手工映射列表 */

	private List<HandlerMapping> handlerMappings;

	/** 此servlet使用的HANDLE适配器列表 */

	private List<HandlerAdapter> handlerAdapters;

	/** List of HandlerExceptionResolvers used by this servlet */

	private List<HandlerExceptionResolver> handlerExceptionResolvers;

	/** RequestToViewNameTranslator used by this servlet */

	private RequestToViewNameTranslator viewNameTranslator;

	/** 这个servlet使用的Flash MMAPLE管理器 */

	private FlashMapManager flashMapManager;

	/** 此servlet使用的VIEW解析器列表 */

	private List<ViewResolver> viewResolvers;

	public void setServletProperty(ServletProperty servletProperty) {

		servletPropertys.add(servletProperty);

	}

	@Override
	protected void onRefresh(WebApplicationContext context) {

		initStrategies(context);

	}

	protected void initStrategies(WebApplicationContext context) {
		/**
		 * 文件解析器
		 */
		initMultipartResolver(context);
		/**
		 * 配置多语言本地化
		 */
		initLocaleResolver(context);
		/**
		 * 样式相关的解析器
		 */
		initThemeResolver(context);
		/**
		 * 资源映射
		 */
		initHandlerMappings(context);
		initHandlerAdapters(context);
		initHandlerExceptionResolvers(context);
		initRequestToViewNameTranslator(context);
		initViewResolvers(context);
		initFlashMapManager(context);
		System.out.println("初始化完成!");
	}

	private void initMultipartResolver(ApplicationContext context) {

		this.multipartResolver = context.getBean(MULTIPART_RESOLVER_BEAN_NAME, MultipartResolver.class);

	}
	
	private void initLocaleResolver(ApplicationContext context) {

	}

	private void initThemeResolver(ApplicationContext context) {

	}

	private void initHandlerMappings(ApplicationContext context) {
		if (this.detectAllHandlerMappings) {  
         
            //默认加载所有的HandlerMapping,  
			/***
			 * 因为该mapping映射的是项目里所有带@controller的类
			 * 而程序需要的是包含在所配置的扫描的指定包下的mapping映射
			 */
            Map<String, HandlerMapping> matchingBeans =  BeansUtil.beansOfType
            												(context, RequestMappingHandlerMapping.class);
                   
            
            if (!matchingBeans.isEmpty()) {  
                //数组中含有多个HandlerMapping  
                this.handlerMappings = new ArrayList<HandlerMapping>(matchingBeans.values());  
             
            }  
        }  
	}

	private void initHandlerAdapters(ApplicationContext context) {
		
		this.handlerAdapters = getDefaultStrategies(context,HandlerAdapter.class);
	   
			
	}

	private void initRequestToViewNameTranslator(ApplicationContext context) {

	}

	private void initViewResolvers(ApplicationContext context) {
		    
		  this.viewResolvers =  getDefaultStrategies(context,ViewResolver.class);
	}

	private void initFlashMapManager(ApplicationContext context) {

	}
	private void initHandlerExceptionResolvers(ApplicationContext context) {

	}
	/**
	 * 创建对象
	 * @param context
	 * @param strategyInterface
	 * @return
	 */
	protected <T> List<T> getDefaultStrategies(ApplicationContext context, Class<T> strategyInterface) {
		
		List<T> sings  = new ArrayList<>();
		
		sings.add((T) getDefaultStrategie(strategyInterface) );
		
		return sings;
	}
	protected Object getDefaultStrategie(Class<?> cls) {
		
		if (cls==ViewResolver.class) {
			
			return ClassUtil.newInstance(ResourceBundleViewResolver.class);
			
		}
        if (cls==HandlerAdapter.class) {
			
			return ClassUtil.newInstance(RequestMappingHandlerAdapter.class);
			
		}
		return null;
	}

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		doDispatch(request, response);
		
	}
	
	protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpServletRequest processedRequest = request;

			ModelAndView mv = null;
			Exception dispatchException = null;
			HandlerMapping mappedHandler = null;
			
			    mappedHandler = getHandler(processedRequest);
			   
			    if (mappedHandler==null) {
			    	
			    	
			    }
				// Determine handler adapter for the current request.
				HandlerAdapter ha = getHandlerAdapter(mappedHandler);
				
				// 返回ModelAndView
				mv = ha.handle(processedRequest, response, mappedHandler);
				//返回view
				applyDefaultViewName(processedRequest, mv);
				//响应给浏览器
				response( response,mv .getView());
				
			
	}
	protected void response(HttpServletResponse response,Object view) {
		
			  if (view instanceof String) {
				  
			  } else if (view instanceof View){
				  
				  View vi = (View) view;
				  
				   //通过视图响应类型获取视图解析器
				  ViewResolver viewResolver = viewResolvers.get(0);
				  
				  try {
					viewResolver.resolveView(vi, response);
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				  
			  }
		
	}
	protected HandlerAdapter getHandlerAdapter(HandlerMapping mappedHandler) {
		
		
		
		return this.handlerAdapters.get(0);	
	}
	/**
	 * 找到hadlermapping
	 * @param processedRequest
	 * @return
	 */
	protected HandlerMapping getHandler(HttpServletRequest processedRequest) {
		
		  String relativeUrl = HttpUtil.getRelativeUrl(processedRequest);
		  HandlerMapping hsd = null;
		  for (HandlerMapping ha:handlerMappings) {
			
			  RequestMappingHandlerMapping hs = (RequestMappingHandlerMapping) ha;
			  
			      if (hs.getUrlPathHelper().
			    		                     getAllUrl().
			    		                         contains(relativeUrl)) {
			    	
			    	 hsd = hs;
			    	 break;
			      }
		  }
		  
		return hsd;
	}
	private void applyDefaultViewName(HttpServletRequest request,  ModelAndView mv) throws Exception {
		
	}
	

	
}
