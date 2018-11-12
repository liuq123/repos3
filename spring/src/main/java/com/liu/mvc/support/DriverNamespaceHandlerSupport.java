package com.liu.mvc.support;

import com.liu.spring.parser.NamespaceHandlerSupport;

public class DriverNamespaceHandlerSupport extends NamespaceHandlerSupport{

	@Override
	public void init() {
	
		  super.registerBeanDefinitionParser("annotation-driven", new AnnotationdrivenXmlParser());
		
	}

}
