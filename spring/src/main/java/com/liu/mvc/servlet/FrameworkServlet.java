package com.liu.mvc.servlet;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.liu.mvc.context.GenericWebApplicationContext;
import com.liu.mvc.context.WebApplicationContext;
import com.liu.mvc.utils.ApplicationContextUtils;
import com.liu.spring.context.ApplicationContext;
import com.liu.spring.util.ClassUtil;

public abstract class FrameworkServlet extends HttpServletBean {
	/**
	 * 容器是否刷新
	 */
	private boolean refreshEventReceived = false;
	/**
	 * 是否发布在tomcat容器当中
	 */
	private boolean publishContext = true;
	/**
	 * springmvc上下文
	 */
	private WebApplicationContext webApplicationContext;

	/**
	 * 创建此servlet的WebApplicationContext
	 */
	@Override
	public void initServletBean() {
		/**
		 * 吧spring上下文设置在这里与servlet关联起来
		 */
		this.webApplicationContext = initWebApplicationContext();

	}

	protected void initFrameworkServlet() {
		
	}

	protected WebApplicationContext initWebApplicationContext() {

		ApplicationContext parent = (ApplicationContext) getServletContext()
				.getAttribute(ApplicationContext.CONTEXT_NAME);

		WebApplicationContext wac = null;
		
		if (wac == null) {

			wac = findWebApplicationContext();
		}
		if (wac == null) {
			/**
			 * 把创建方式可以改成反射有参数构造
			 */
		 	wac = createWebApplicationContext(parent);
		    GenericWebApplicationContext  s = (GenericWebApplicationContext) wac;
		    s.startContext();
		    wac = s;
		}
		/**
		 * 初始化springmvc容器
		 */
		if (!this.refreshEventReceived) {
			
			onRefresh(wac);
		}
		/**
		 * 发布在tomcat容器中
		 */
		if (this.publishContext) {
			
			String attrName = getServletContextAttributeName();
			
			getServletContext().setAttribute(attrName, wac);
		
		}

		return wac;
	}
    protected String getServletContextAttributeName() {
    	
    	
    	return WebApplicationContext.SERVLET_CONTEXT_NAME;
    }
    
	protected WebApplicationContext findWebApplicationContext() {

		String attrName = getServletContextAttributeName();

		if (attrName == null) {
			return null;
		}

	 WebApplicationContext wac = (WebApplicationContext)ApplicationContextUtils.getWebApplicationContext(super.getServletContext(), attrName);
   
		return wac;
	}

	protected String getContextAttribute() {

		return ApplicationContext.CONTEXT_NAME;
	}

	protected WebApplicationContext createWebApplicationContext(ApplicationContext parent) {

		Class<?> contextClass = getContextClass();
		
		GenericWebApplicationContext newInstance = (GenericWebApplicationContext) ClassUtil.newInstance(contextClass);
		
		newInstance.setParentContext(parent);
		newInstance.setServletContext(getServletContext());
		
		return newInstance;
		
	}

	protected Class<?> getContextClass() {
		
		return GenericWebApplicationContext.class;
	}
	protected abstract void onRefresh(WebApplicationContext context);
	
	@Override
	protected final void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		processRequest(request, response);
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

	@Override
	protected final void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		processRequest(request, response);
	}
	
	
	
	protected final void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long startTime = System.currentTimeMillis();
		

	

		try {
			doService(request, response);
		}
		catch (ServletException | IOException ex) {
			ex.printStackTrace();
			throw ex;
		}
		catch (Throwable ex) {
			ex.printStackTrace();
		
		}

		finally {
		
			}
		
	}
	protected abstract void doService(HttpServletRequest request, HttpServletResponse response)
			throws Exception;

}
