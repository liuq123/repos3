package com.liu.mvc.context;

import com.liu.spring.context.ApplicationContext;

public interface WebApplicationContext extends ApplicationContext{
	
     public static final String SERVLET_CONTEXT_NAME = WebApplicationContext.class+".mvc" ;   
	
	  void setParentContext(ApplicationContext applicationContext);
	  ApplicationContext getParentApplicationContext();
}
