package com.liu.spring.factory;

public interface BeanFactory {
	
	   <T> T getBean(String name,Class<T> cls); 
	    
	   Object getBean(String name); 
	
	
	
}
