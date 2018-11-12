package com.liu.spring.parser;

import com.liu.spring.factory.BeanFactory;
import com.liu.spring.register.RegisterWare;

public class ParserContext {
  
	 private BeanFactory beanFactory ;
	 
	 private RegisterWare registerWare;

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	public RegisterWare getRegisterWare() {
		return registerWare;
	}

	public void setRegisterWare(RegisterWare registerWare) {
		this.registerWare = registerWare;
	}
	 
	 
}
