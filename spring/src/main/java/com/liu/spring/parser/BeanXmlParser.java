package com.liu.spring.parser;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;

import com.liu.spring.factory.BeanFactory;
import com.liu.spring.model.BeanDefinition;
import com.liu.spring.model.GenericBeanDefinition;
import com.liu.spring.model.MutablePropertyValues;
import com.liu.spring.model.Node;

public class BeanXmlParser implements XmlParser {

	public <T> GenericBeanDefinition parserElement( Node<T> e2, ParserContext parserContext) {
		
		return parseBeanElement(e2);
	}

	public <T> GenericBeanDefinition parseBeanElement(Node<T> e2) {
		GenericBeanDefinition bean = new GenericBeanDefinition();
		this.parseProperty(bean, (Element) e2.getNode());
		this.setBeanAtti(bean, (Element) e2.getNode());
		this.parseConstructor(bean, (Element) e2.getNode());
		
	     return bean;
	}

	public <T> void parseProperty(GenericBeanDefinition bean, Element e2) {
		List<Element> elements = e2.elements();
		MutablePropertyValues propertyValues = new MutablePropertyValues();
		for (Element e : elements) {

			if ("property".equals(e.getName())) {
				String name = null;
				String value = null;
				Attribute attribute = e.attribute("name");
				if (attribute != null) {
					name = attribute.getValue();
					name = attribute.getValue();
				}
				Attribute attribute1 = e.attribute("value");
				if (attribute1 != null) {
					propertyValues.getPropertyName().add(name);
					value = attribute1.getValue();

				} else {
					Attribute attribute2 = e.attribute("ref");

					if (attribute2 != null) {
						value = attribute2.getValue();
						propertyValues.addRefName(name);

					}
				}
				propertyValues.getPropertyName().add(value);
				propertyValues.getValueMap().put(name, value);
				bean.setPropertyValues(propertyValues);

			}

		}

	}

	public <T> void setBeanAtti(GenericBeanDefinition bean, Element e2) {

		if (e2.attribute("id") != null) {
			String id = e2.attribute("id").getValue();
			bean.setBeanId(id);
		}
		if (e2.attribute("abstract") != null) {
			String value = e2.attribute("abstract").getValue();
			if ("true".contentEquals(value)) {
				boolean abstratValue = true;
				bean.setAbstractFlag(abstratValue);
			}
		}
		if (e2.attribute("class") != null) {
			String className = e2.attribute("class").getValue();
			bean.setBeanClass(className);
		}

		if (e2.attribute("scope") != null) {
			String scope = e2.attribute("scope").getValue();
			bean.setScope(scope);
		}
		if (e2.attribute("init-method") != null) {
			String method = e2.attribute("init-method").getValue();
			bean.setInitMethodName(method);
		}
		if (e2.attribute("destory-method") != null) {
			String destory = e2.attribute("destory-method").getValue();
			bean.setDestroyMethodName(destory);
		}
		if (e2.attribute("parent") != null) {

			String parId = e2.attribute("parent").getValue();
			bean.setParentId(parId);

		}

	}

	public <T> void parseConstructor(GenericBeanDefinition bean, Element e) {
		// TODO Auto-generated method stub

	}

}
