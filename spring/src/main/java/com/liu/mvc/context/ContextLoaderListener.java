package com.liu.mvc.context;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ContextLoaderListener extends ContextLoader implements ServletContextListener{
	
	
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent even) {
		
		initWebApplicationContext(even.getServletContext());
		
		
	}

}
