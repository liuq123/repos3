package com.liu.spring.context;


import com.liu.spring.factory.BeanFactory;

public interface ApplicationContext extends BeanFactory{
	
    public static final String CONTEXT_NAME = ApplicationContext.class+".root";
   
}
