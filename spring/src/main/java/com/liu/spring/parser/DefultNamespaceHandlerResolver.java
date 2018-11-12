package com.liu.spring.parser;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.liu.spring.factory.BeanFactory;
import com.liu.spring.factory.DefaultBeanFactory;
import com.liu.spring.util.ClassUtil;

public class DefultNamespaceHandlerResolver implements NamespaceHandlerResolver {

	public void loadParser(BeanFactory beanFactory) {
		
		List<Class<?>> allSupportClass = loadAllSupportClass();
		
         for (Class<?> cs:allSupportClass) {
        	 
         NamespaceHandlerSupport newInstance = (NamespaceHandlerSupport)ClassUtil.newInstance(cs);
          newInstance.init();
         
          Map<String,XmlParser> parsers = newInstance.getParsers();
          
          Set<Entry<String,XmlParser>> entrySet = parsers.entrySet();
          
           		if (beanFactory instanceof DefaultBeanFactory) {
        	   
           			DefaultBeanFactory defaultBeanFactory = (DefaultBeanFactory) beanFactory;
           			
           			for (Entry<String,XmlParser> en:entrySet ) {
           				
           				defaultBeanFactory.addSpaceHandlerSupport(en.getKey(), en.getValue());
            	       
                    }
           }
               
        
         }
		
	}

	private List<Class<?>> loadAllSupportClass() {

		Class<?> forName = ClassUtil.forName("com.liu.spring.parser.NamespaceHandlerSupport");

		return ClassUtil.getAllClass(forName);

	}

}
