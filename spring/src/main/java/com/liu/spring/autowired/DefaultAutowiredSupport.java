package com.liu.spring.autowired;

import java.util.Map.Entry;
import java.util.Set;

import com.liu.spring.annocation.Controller;
import com.liu.spring.annocation.Service;
import com.liu.spring.context.AbstractApplicationContext;
import com.liu.spring.factory.DefaultBeanFactory;
import com.liu.spring.model.BeanDefinition;
import com.liu.spring.model.GenericBeanDefinition;
import com.liu.spring.register.SingleRegist;
import com.liu.spring.util.ClassUtil;

public class DefaultAutowiredSupport extends AbstractAutowiredSupport {

	@Override
	public void injection(AbstractApplicationContext abstractApplicationContext) {

		DefaultBeanFactory beanFactory = (DefaultBeanFactory) abstractApplicationContext.getBeanFactory();

		SingleRegist singleRegistWare = abstractApplicationContext.getSingleRegistWare(abstractApplicationContext);

		Set<Entry<String, BeanDefinition>> entrySet = beanFactory.getBeanMap().entrySet();

		for (Entry<String, BeanDefinition> es : entrySet) {

			GenericBeanDefinition ge = (GenericBeanDefinition) es.getValue();
			/**
			 * 创建实体并且进行依赖注入
			 */
			singleRegistWare.getBean(ge.getBeanId(), beanFactory, abstractApplicationContext.getRealybeanFactory());
			
		
		
		}

	}

}
