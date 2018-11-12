package com.liu.mvc.beans;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;


import com.liu.mvc.ancocation.RequestMethod;
import com.liu.spring.factory.BeanFactory;
import com.liu.spring.util.GetListFactory;
public class HandlerMethod {
      /**
       * 方法所对应路径
       */
	   private String methodUrl;
	   
	   private String MethodName;
	   /**
	    * 方法名 参数类型
	    */
	   private Map<String,Class<?>> parameterNameAndType = GetListFactory.buildHashMap();
	   
	   private  List<RequestMethod>  requestMethod;
	   
	   private List<String> parameterName = GetListFactory.buildArrayList();
	   
	   private  Method method;
	   
	   private Class<?> controllerClass;
	   //真正的实体工厂
	   private  BeanFactory beanFactory;
	   //相对路径
	   private String relativeUrl;
	   
	  public String getMethodUrl() {
		  
		return methodUrl;
	  }

	public void setMethodUrl(String methodUrl) {
		this.methodUrl = methodUrl;
	}

	public String getMethodName() {
		return MethodName;
	}

	public void setMethodName(String methodName) {
		MethodName = methodName;
	}

	public Map<String, Class<?>> getParameterNameAndType() {
		
		return parameterNameAndType;
	}

	public void setParameterName(Map<String, Class<?>> parameterNameAndType) {
		
		this.parameterNameAndType.putAll(parameterNameAndType);
	}

	public List<RequestMethod> getRequestMethod() {
		return requestMethod;
	}


	public void setRequestMethod(List<RequestMethod> requestMethod) {
		this.requestMethod = requestMethod;
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public void setControllerClass(Class<?> controllerClass) {
		this.controllerClass = controllerClass;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}
	
	
	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public List<String> getParameterName() {
		return parameterName;
	}

	public void setParameterName(List<String> parameterName) {
		this.parameterName = parameterName;
	}

	public String getRelativeUrl() {
		return relativeUrl;
	}

	public void setRelativeUrl(String relativeUrl) {
		this.relativeUrl = relativeUrl;
	}

	@Override
	public String toString() {
		return "HandlerMethod [methodUrl=" + methodUrl + ", MethodName=" + MethodName + ", parameterName="
				+ parameterName + ", requestMethod=" + requestMethod + ", controllerClass=" + controllerClass + "]";
	}
	   
	   
	   
}
