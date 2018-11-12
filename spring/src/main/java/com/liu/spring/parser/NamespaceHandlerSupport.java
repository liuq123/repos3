package com.liu.spring.parser;

import java.util.HashMap;
import java.util.Map;

import com.liu.spring.factory.BeanFactory;

public abstract class NamespaceHandlerSupport implements NamespaceHandler {

	public final Map<String, XmlParser> parsers = new HashMap<String, XmlParser>();
	
	
	public void registerBeanDefinitionParser(String parseName, XmlParser pareser) {
		
		parsers.put(parseName, pareser);
     
	}

	public Map<String, XmlParser> getParsers() {
		return parsers;
	}
	
	
	

}
