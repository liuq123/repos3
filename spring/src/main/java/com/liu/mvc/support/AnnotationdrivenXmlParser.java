package com.liu.mvc.support;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.liu.mvc.ancocation.Mapping;
import com.liu.mvc.ancocation.RequestMapping;
import com.liu.mvc.ancocation.RequestMethod;
import com.liu.mvc.beans.HandlerMethod;
import com.liu.mvc.mapping.HandlerMapping;
import com.liu.mvc.mapping.RequestMappingHandlerMapping;
import com.liu.spring.annocation.Controller;
import com.liu.spring.factory.BeanFactory;
import com.liu.spring.model.GenericBeanDefinition;
import com.liu.spring.model.MutablePropertyValues;
import com.liu.spring.model.Node;
import com.liu.spring.parser.ParserContext;
import com.liu.spring.parser.XmlParser;
import com.liu.spring.util.ClassUtil;
import com.liu.spring.util.GetListFactory;
import com.liu.spring.util.StringUtil;
/**
 * 映射handmapping
 * @author Administrator
 *
 *
 */
public class AnnotationdrivenXmlParser implements XmlParser{
	
	private final  Class<Controller>  cls =  Controller.class;
	
	private final  Class<RequestMapping>  reqMp =  RequestMapping.class;
	
	private BeanFactory beanFactory;
	
	private final Class<RequestMappingHandlerMapping> reqClass = RequestMappingHandlerMapping.class;
	
	public <T> GenericBeanDefinition parserElement(Node<T> e2, ParserContext parserContext) {
		
		List<Class<?>> cos = new ArrayList<Class<?>>();
		
		List<String> allPageNames = ClassUtil.getAllPageNames();
		
		for (String name: allPageNames ) {
			
			cos.addAll(ClassUtil.annotationClass(name, cls));
			
			
		}
		
		/**
		 * 映射handmpping
		 */
        for (Class<?> s:cos) {
			
			registBeanDefinition(parserContext, s);
			
		 }
		
		
		
		
		return null;
	}
	
	private void registBeanDefinition(ParserContext parserContext,Class<?> cls) {
	
	        if (ClassUtil.isContainAnnotation(cls, reqMp)) {
	        	//controller上面的mapping对应的多个url
	        	List<String> annotationValues = this.getClassMapiAnnotationValues(cls);
	        	  
	        	  if (annotationValues.size()>0) {
	        		  	
	        		  for (String v:annotationValues) { 
	        			  	/**
	        			  	 * 创建mapping
	        			  	 */
	        			  	 RequestMappingHandlerMapping createReqMapping =  createReqMapping(cls, v);
	        			  	 
	        			  		    //生成
	        			  	 GenericBeanDefinition beanDefinition = generateBeanDefinition(createReqMapping);
	        			  		   //注册
	        			    parserContext.getRegisterWare().registBention(parserContext.getBeanFactory(), beanDefinition);
	        			    
	        		  }
	        		  
	        	  }
	        	
	        	  
	         }  else {
		    	             /**
	        			  	 * 创建mapping
	        			  	 */
	        		        RequestMappingHandlerMapping createReqMapping =  createReqMapping(cls, HandlerMapping.DEFULT_CONTROLLER_URL);
	   
	        			  		    	//生成
	        			  	GenericBeanDefinition beanDefinition = generateBeanDefinition(createReqMapping);
	        			  		 		//注册
	        			  	parserContext.getRegisterWare().registBention(parserContext.getBeanFactory(), beanDefinition);
	        			  		      
	        			
	
	         }
		
		
	}
	private RequestMappingHandlerMapping createReqMapping(Class<?> cls,String url) {
		
	    RequestMappingHandlerMapping maping = new RequestMappingHandlerMapping();
	    
	    /**
	     * 注册HandlerMethod
	     */
		List<Method> methods =  ClassUtil.getContainAnnotationMethods(cls,reqMp);
			
		 for (Method me:methods) {
			 
			   if (ClassUtil.MethodIsContainAnnotation(me,  reqMp)) {
				   	//方法上面对应的多个url
				   List<String> annotationValues = this.getMethodMapiAnnotationValues(me);
				       
				         if (annotationValues.size()>0) {
				        	 
				        	 for (String value:annotationValues) {
				        		 	/**			        		
				        		 	 * handler key
				        		 	 */
				        		  String hadnlerKey = url+value;
						    	   maping.registHandlerMethod(hadnlerKey, this.createHandlerMethod(value, me,cls));
						    	   maping.getUrlPathHelper().addAllUrl(url+value); 
						     }
				         }
			   }
			 
		 }
		 maping.getUrlPathHelper().setControllerUrl(url);
		 maping.setMappingNameByController(cls,url);
		maping.setControllerBeanId(getControllerBeanId(cls));
	
		return maping;
	}
	//获取controllerbeanid
	private String getControllerBeanId(Class<?> cls) {
		
		return StringUtil.getLowerCaseFirstOneBeanName(cls.getName());
	}
	/**
	 * 获取方法上的mapping路径url
	 * 
	 * @param me
	 * @return
	 */
	private List<String> getMethodMapiAnnotationValues(Method me){
		
		List<String> buildArrayList = GetListFactory.buildArrayList();
		 
		     if (ClassUtil.MethodIsContainAnnotation(me,  reqMp)) {
		    	 
		    	 RequestMapping requestMapping = me.getAnnotation(reqMp);
		    	 String[] value = requestMapping.value();
		
		    	  if (value!=null) {
		    		  
		    		   for (int i=0;i<value.length;i++) {
		    			    //不为空
		    			    if (!StringUtil.isNullOrEmpty(value[i])) {
		    			    	
		    			    	buildArrayList.add(value[i]);
		    			    }
		    			   
		    		   }
		    	  }
		    	 
		    	 
		     }
	
		return buildArrayList;	
	}
	/**
	 * 获取类上面的hampping value的值
	 * @param me
	 * @return
	 */
   private List<String> getClassMapiAnnotationValues(Class<?> cls){
		
		List<String> buildArrayList = GetListFactory.buildArrayList();
	
	     if (ClassUtil.isContainAnnotation(cls, reqMp)) {
	    	 
	    	 RequestMapping requestMapping = cls.getAnnotation(reqMp);
	    	 String[] value = requestMapping.value();
	    	 
	    	  if (value!=null) {
	    		  
	    		   for (int i=0;i<value.length;i++) {
	    			    //不为空
	    			    if (!StringUtil.isNullOrEmpty(value[i])) {
	    			    	
	    			    	buildArrayList.add(value[i]);
	    			    }
	    			   
	    		   }
	    	  }
	    	 
	    	 
	     }
	
		return buildArrayList;
		
	}
	/**
	 * 创建HandlerMethod
	 * @param cls
	 * @param url
	 * @param mapings
	 */
	private HandlerMethod createHandlerMethod( String methodUrl,Method me,Class<?> cls){
		
		HandlerMethod meh = new HandlerMethod();
	       String methodName = me.getName();
		   List<RequestMethod> methods = this.getRequestMethods(me);
		   Map<String,Class<?>> parameterNames = ClassUtil.getParameterNameAndvalue(me,cls);
		   List<String> parameterName = ClassUtil.getParameterName(me, cls);
		   meh.setMethodName(methodName);
		   meh.setRequestMethod(methods);
	     	meh.setParameterName(parameterNames);
	     	meh.setMethodUrl(methodUrl);
	     	meh.setControllerClass(cls);
	     	meh.setMethodName(me.getName());
			meh.setParameterName(parameterName);
			meh.setMethod(me);
			return meh;

	}
	
	private GenericBeanDefinition generateBeanDefinition(RequestMappingHandlerMapping maping) {
		
		GenericBeanDefinition ge = new GenericBeanDefinition();
		 ge.setBeanClass(this.getGenericBeanClassName(maping.getClass()));
		 
		 MutablePropertyValues mo = new MutablePropertyValues();
		 Map<String, Object> keyAndValue = ClassUtil.getKeyAndValue(maping);
		 List<String> allFieldName = ClassUtil.getAllFieldName(RequestMappingHandlerMapping.class);
		 mo.getValueMap().putAll(keyAndValue);
		 mo.getPropertyName().addAll(allFieldName);
		 ge.setPropertyValues(mo);
		 ge.setBeanId(maping.getMappingName());
		return ge;
		
	}
	private String getGenericBeanClassName(Class<?> cs){
		
		return RequestMappingHandlerMapping.class.getName();
	}
	private List<RequestMethod> getRequestMethods(Method me){
	   
		List<RequestMethod> requestMethods = GetListFactory.buildArrayList();
		RequestMapping annotation = me.getAnnotation(reqMp);
		
		if (ClassUtil.MethodIsContainAnnotation(me, reqMp)) {
			  
			  RequestMethod[] value = me.getAnnotation(reqMp).method();
			  for (int i=0;i<value.length;i++) {
				  
				  if (!StringUtil.isNullOrEmpty(value[i])) {
					  
					  requestMethods.add(value[i]);
				  }
			  }
		}
		return requestMethods;
	}
	public static void main(String[] args) {
		
		AnnotationdrivenXmlParser s = new AnnotationdrivenXmlParser();
		
		s.parserElement(null, null);
		
		
	}
}
