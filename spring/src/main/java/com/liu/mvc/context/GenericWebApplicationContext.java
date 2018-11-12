package com.liu.mvc.context;

import javax.servlet.ServletContext;

import com.liu.spring.context.AbstractApplicationContext;
import com.liu.spring.context.ApplicationContext;

public class GenericWebApplicationContext extends AbstractApplicationContext{
     	
	private ServletContext servletContext;
	
	
	public GenericWebApplicationContext() {
		
	}
	
	public void startContext() {
		
		setConfigLocations( this.getLocation());
		
		refash();
		
	}
    public GenericWebApplicationContext(String... locs) {
		
		this(true,locs);
		
	}
	 public GenericWebApplicationContext(boolean isfalsh,String... locs) {
		
		if (isfalsh) {
			
			setConfigLocations( locs);
			refash();
			
		}
		
		
	}
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	 private String[] getLocation() {
		
	
		
		return  new String[]{"spring-bean.xml"};
	}
	
	
	
	
	
	
}	
