package com.liu.mvc.support;

import javax.servlet.ServletContext;

import com.liu.mvc.beans.ServletProperty;

public class ServletBeanWrapper implements BeanWrapper {
    
	@Override
	public ServletProperty loadServletProperty(ServletContext context) {
		
		ServletProperty pro = new ServletProperty();
		pro.setServletName("dispatcher");
		pro.setServletClass("org.springframework.web.servlet.DispatcherServlet");
		pro.getMapping().put("dispatcher","/");
		pro.getParam().put("contextConfigLocation","classpath:springConfig/dispatcher-servlet.xml");
		pro.setLoadOnStartup(0);
		
		
		
		return pro;
	}
	
}
