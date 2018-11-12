package com.liu.spring.parser;


import com.liu.spring.model.GenericBeanDefinition;
import com.liu.spring.model.Node;

public interface XmlParser {
	
     public <T> GenericBeanDefinition parserElement( Node<T> e2,ParserContext parserContext);
     
}
