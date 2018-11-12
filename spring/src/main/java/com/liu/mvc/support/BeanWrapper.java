package com.liu.mvc.support;

import javax.servlet.ServletContext;

import com.liu.mvc.beans.ServletProperty;

public interface BeanWrapper extends ConfigurablePropertyAccessor{
        
	ServletProperty loadServletProperty(ServletContext context);
}
