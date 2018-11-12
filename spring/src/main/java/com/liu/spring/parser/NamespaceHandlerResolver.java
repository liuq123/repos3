package com.liu.spring.parser;

import com.liu.spring.factory.BeanFactory;

public interface NamespaceHandlerResolver {
	
   public void  loadParser(BeanFactory beanFactory);
   
}
