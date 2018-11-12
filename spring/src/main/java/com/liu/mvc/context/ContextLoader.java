package com.liu.mvc.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletContext;
import com.liu.spring.context.ApplicationContext;
import com.liu.spring.context.ClasspathXmlApplicationContext;

public class ContextLoader {
	/***
	 * 父类容器
	 * 
	 */
	private ClasspathXmlApplicationContext context;

	private static volatile ApplicationContext currentContext;

	private static final Map<ClassLoader, ApplicationContext> currentContextPerThread = new ConcurrentHashMap<ClassLoader, ApplicationContext>(
			1);

	public ApplicationContext initWebApplicationContext(ServletContext servletContext) {

		this.context = createWebApplicationContext(servletContext);
		this.context.startContext();
		
		/**
		 * 把spring容器放在context中
		 */
		servletContext.setAttribute(ApplicationContext.CONTEXT_NAME, this.context);
		
		ClassLoader ccl = Thread.currentThread().getContextClassLoader();
		
		if (ccl == ContextLoader.class.getClassLoader()) {
			currentContext = this.context;

		} else if (ccl != null) {
			currentContextPerThread.put(ccl, this.context);
		}
		return this.context;

	}

	public ClasspathXmlApplicationContext createWebApplicationContext(ServletContext servletContext) {
			
		return new ClasspathXmlApplicationContext();
	}

}
