package com.liu.spring.register;

import com.liu.spring.factory.BeanFactory;
import com.liu.spring.factory.DefaultBeanFactory;
import com.liu.spring.model.BeanDefinition;
import com.liu.spring.model.GenericBeanDefinition;
import com.liu.spring.util.ClassUtil;

public class BeanDefinitionRegisterWare implements RegisterWare{

	public int registBention(BeanFactory beanFactory, BeanDefinition bean) {
		
		 if (beanFactory instanceof DefaultBeanFactory) {
			     
			 DefaultBeanFactory deful = (DefaultBeanFactory)beanFactory;
			 GenericBeanDefinition ge = (GenericBeanDefinition)bean;    
			   deful.getBeanMap().put(ge.getBeanId()+"",ge );
		     deful.addSingletonBeanNames(ClassUtil.forName((String)ge.getBeanClass()), ge.getBeanId());
			  return 1;
		 }
		
		
		return 0;

	}
          
}
