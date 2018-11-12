package com.liu.mvc.mapping;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.liu.mvc.ancocation.ResponseBody;
import com.liu.mvc.beans.HandlerMethod;


public abstract class AbstractHandlerMethodMapping extends AbstractHandlerMapping{
	
	
	private final Map<String, HandlerMethod> mappingLookup = new LinkedHashMap<String, HandlerMethod>();
	
	private final Map<String, List<HandlerMethod>> nameLookup = new ConcurrentHashMap<>();
	
	public   HandlerMethod getHandlerMethodRelativeUrl(String url) {
		
	
		
		return  mappingLookup.get(url);
	}
	 public void registHandlerMethod(String url,HandlerMethod handlerMethod) {
		 
		 mappingLookup.put(url, handlerMethod);
	 }
	 
	public  List<HandlerMethod> getHandlerMethodListByName(String name) {
		
		
	 return	 nameLookup.get(name);
	}
	
	public void registHandlerMethodList(String name,List<HandlerMethod> HandlerMethods) {
		
		nameLookup.put(name, HandlerMethods);
	}
	public Map<String, HandlerMethod> getMappingLookup() {
		return mappingLookup;
	}
	
}
	
	

