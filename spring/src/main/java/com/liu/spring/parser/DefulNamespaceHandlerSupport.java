package com.liu.spring.parser;

public class DefulNamespaceHandlerSupport extends NamespaceHandlerSupport {

	public void init() {
		 
		this.registerBeanDefinitionParser("bean", new BeanXmlParser());
		
	}
	
}
