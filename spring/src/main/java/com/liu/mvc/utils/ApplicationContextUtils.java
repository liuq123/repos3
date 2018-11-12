package com.liu.mvc.utils;

import javax.servlet.ServletContext;

import com.liu.spring.context.ApplicationContext;

public class ApplicationContextUtils {
	public static ApplicationContext getWebApplicationContext(ServletContext sc, String attrName) {

		Object attr = sc.getAttribute(attrName);
		if (attr == null) {
			return null;
		}
		if (attr instanceof RuntimeException) {
			throw (RuntimeException) attr;
		}
		if (attr instanceof Error) {
			throw (Error) attr;
		}
		if (attr instanceof Exception) {
			throw new IllegalStateException((Exception) attr);
		}
		if (!(attr instanceof ApplicationContext)) {
			throw new IllegalStateException("Context attribute is not of type WebApplicationContext: " + attr);
		}
		return (ApplicationContext) attr;
	}

}
