package com.liu.spring.parser;


import com.liu.spring.model.Resource;

public interface XmlReader extends SourceReader{
	
    public void  loadBeanDefinition(Resource re) ;
  
  
}
