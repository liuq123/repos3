package com.liu.spring.parser;

public interface NamespaceHandler {

	public void registerBeanDefinitionParser(String parseName, XmlParser pareser);

	public void init();
}
