package com.liu.spring.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.liu.spring.model.BeanDefinition;
import com.liu.spring.parser.XmlParser;

public class DefaultBeanFactory extends AbstractBeanFactory {

	/**
	 * BeanDefinition��� key��BeanDefinition ��beanid
	 */
	private Map<String, BeanDefinition> beanMap = new HashMap<String, BeanDefinition>();
	private final Map<Class<?>, String[]> allBeanNamesByType = new ConcurrentHashMap<Class<?>, String[]>(64);
	private final Map<Class<?>, String[]> singletonBeanNamesByType = new ConcurrentHashMap<Class<?>, String[]>(64);
	
	/**
	 * �����ռ䴦�����
	 */
	private final Map<String, XmlParser> spacehandles = new HashMap<String, XmlParser>();
	
	/**
	 * BeanDefinition��� key��BeanDefinition ��class �ö����ǵ���ʱ��ֵ
	 */

	public Map<String, BeanDefinition> getBeanMap() {

		return beanMap;
	}

	public BeanDefinition getByName(String id) {

		return beanMap.get(id);
	}

	public Map<Class<?>, String[]> getAllBeanNamesByType() {

		return allBeanNamesByType;
	}

	public Map<Class<?>, String[]> getSingletonBeanNamesByType() {

		return singletonBeanNamesByType;
	}

	public void addSpaceHandlerSupport(String name, XmlParser namespaceHandler) {
              
		 spacehandles.put(name, namespaceHandler);
	}
	public XmlParser getSpaceHandlerSupport(String name) {
		
		return  spacehandles.get(name);
	}
	public void addSingletonBeanNames(Class<?> cls,String beanName) {
		
		singletonBeanNamesByType.put(cls, new String[] {beanName});
		
	}
}
