package com.liu.mvc.beans;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.liu.mvc.context.GenericWebApplicationContext;
import com.liu.mvc.mapping.HandlerMapping;
import com.liu.mvc.mapping.RequestMappingHandlerMapping;
import com.liu.spring.context.ApplicationContext;
import com.liu.spring.factory.DefaultBeanFactory;
import com.liu.spring.factory.RealyAbstractBeanFactory;
import com.liu.spring.model.BeanDefinition;
import com.liu.spring.model.GenericBeanDefinition;
import com.liu.spring.util.GetListFactory;
public class BeansUtil {
   /**
    * 加载所有benn根据类型
    * @param context
    * @return
    */
	public static Map<String, HandlerMapping> beansOfType(ApplicationContext context,Class<?> cls) {
		
		    if (context instanceof GenericWebApplicationContext) {
		    	Map<String, HandlerMapping> hdmp = GetListFactory.buildHashMap();
		    	GenericWebApplicationContext ge = (GenericWebApplicationContext) context;
		    	DefaultBeanFactory beanFactory = (DefaultBeanFactory) ge.getBeanFactory();
		    	RealyAbstractBeanFactory realybeanFactory = (RealyAbstractBeanFactory) ge.getRealybeanFactory();
		    	
		       Set<Entry<String,BeanDefinition>> beans = beanFactory.getBeanMap().entrySet();
		       
		    	for (Entry<String,BeanDefinition> be:beans) {
		    		
		    		GenericBeanDefinition gs = (GenericBeanDefinition) be.getValue();
		    		
		    		 if (((String)gs.getBeanClass()).equals(cls.getName())) {
		    			 /**
		    			  * 获取beanid
		    			  */
		    			 String beanId = gs.getBeanId();
		    			 RequestMappingHandlerMapping object = (RequestMappingHandlerMapping) realybeanFactory.getRentyMap().get(beanId);
		    			 Map<String, HandlerMethod> mappingLookup = object.getMappingLookup();
		    			 //设置bean工厂
		    			 for (HandlerMethod hd:mappingLookup.values()) {
		    				 
		    				  hd.setBeanFactory(realybeanFactory);
		    				  
		    			 }
		    			 
		    			 hdmp.put( beanId , object);
		    			  
		    		 }
		    		
		    	}
		    	
		    	return hdmp;
		    }
		
		return  null;
	}

}
