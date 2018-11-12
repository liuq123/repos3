package com.liu.spring.factory;

import com.liu.spring.context.ApplicationContext;
import com.liu.spring.register.BeanDefinitionRegisterWare;
import com.liu.spring.register.RegisterWare;

public class AbstractBeanFactory implements ApplicationContext {

	protected ApplicationContext applicationContext;
	/**
	 * 记录BeanDefinition的数量
	 */
	protected int beanMapCount = 0;
	
	/**
	 * 获取注册器
	 * 
	 * @return
	 */
	protected RegisterWare getRegist() {

		return new BeanDefinitionRegisterWare();
	}

	protected int getBeanMapCount() {

		return beanMapCount;
	}

	public void addCount(int count) {

		this.beanMapCount += count;
	}

	@Override
	public <T> T getBean(String name, Class<T> cls) {
		
		return applicationContext.getBean(name, cls);
	}
	
	@Override
	public Object getBean(String name) {

		return applicationContext.getBean(name);
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
