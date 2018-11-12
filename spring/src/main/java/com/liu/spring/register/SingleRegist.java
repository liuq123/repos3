package com.liu.spring.register;

import com.liu.spring.factory.BeanFactory;
import com.liu.spring.model.GenericBeanDefinition;

public interface SingleRegist {
         public Object getBean(String name,BeanFactory beanFactory,
        		  BeanFactory realybeanFactory);
         
         public void assignment(Object obj,GenericBeanDefinition gebd); 
         
         public Object getBean(Class<?> name,BeanFactory beanFactory,
       		  BeanFactory realybeanFactory);
        
}
