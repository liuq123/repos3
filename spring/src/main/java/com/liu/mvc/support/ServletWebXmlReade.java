package com.liu.mvc.support;


import javax.servlet.ServletContext;

import com.liu.mvc.beans.ServletProperty;
import com.liu.mvc.servlet.DispatcherServlet;
import com.liu.mvc.servlet.HttpServletBean;

public class ServletWebXmlReade implements ServletReader {
    
	  private BeanWrapper beanWrapper;

	public ServletWebXmlReade(BeanWrapper beanWrapper) {
	             
		   this.beanWrapper = beanWrapper;
	}
 
	@Override
	public void initServletPrConfigure(HttpServletBean httpServletBean,ServletContext context) {
		
		DispatcherServlet ht = (DispatcherServlet)httpServletBean;
		
		ServletProperty loadServletProperty = beanWrapper.loadServletProperty(context);
		
		ht.setServletProperty(loadServletProperty);
		
	}
	   
	    
	   
}
