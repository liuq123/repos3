package com.liu.spring.iparse;

import com.liu.spring.parser.IXmlParser;
import com.liu.spring.parser.NamespaceHandlerSupport;

public class INamespaceHandlerSupport extends NamespaceHandlerSupport{
	
	public void init() {
		
	  super.registerBeanDefinitionParser("dateformat", new IXmlParser());
		
		
	 }

}
