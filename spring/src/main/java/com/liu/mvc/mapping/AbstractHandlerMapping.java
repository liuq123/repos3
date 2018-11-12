package com.liu.mvc.mapping;

import java.util.ArrayList;
import java.util.List;

import com.liu.spring.util.StringUtil;

public abstract class AbstractHandlerMapping implements HandlerMapping {

	private final List<HandlerInterceptor> adaptedInterceptors = new ArrayList<HandlerInterceptor>();
	/**
	 * 所对应容器中controller
	 */
	private String controllerBeanId;
	
	private UrlPathHelper urlPathHelper = new UrlPathHelper();
	
	private String mappingName;
	
	public UrlPathHelper getUrlPathHelper() {
		return urlPathHelper;
	}
	
	public List<HandlerInterceptor> getAdaptedInterceptors() {

		return adaptedInterceptors;

	}

	public void addHandlerInterceptor(HandlerInterceptor handlerInterceptor) {

		adaptedInterceptors.add(handlerInterceptor);

	}
	

	public String getMappingName() {
		return mappingName;
	}
	/**
	 * 根据controller bean名字生成name
	 * @param controllerName
	 * @return
	 */
	public void setMappingNameByController( Class<?> cls,String controllerUrl) {
		
	  this.mappingName =  controllerUrl+"-"+StringUtil.getLowerCaseFirstOneBeanName(cls.getName())+
			                                                             HandlerMapping.MAPPING_NAME_POST;
		
		
		
	}

	public void setUrlPathHelper(UrlPathHelper urlPathHelper) {
		this.urlPathHelper = urlPathHelper;
	}

	public String getControllerBeanId() {
		return controllerBeanId;
	}

	public void setControllerBeanId(String controllerBeanId) {
		this.controllerBeanId = controllerBeanId;
	}

	
	
		
	

	
}
