package com.liu.spring.parser;

import java.util.List;

import org.dom4j.Element;

import com.liu.spring.factory.AbstractBeanFactory;
import com.liu.spring.factory.BeanFactory;
import com.liu.spring.factory.DefaultBeanFactory;
import com.liu.spring.model.BeanDefinition;
import com.liu.spring.model.GenericBeanDefinition;
import com.liu.spring.model.Node;
import com.liu.spring.model.Resource;
import com.liu.spring.reflex.Reflex;
import com.liu.spring.reflex.ReflexImpl;
import com.liu.spring.register.BeanDefinitionRegisterWare;
import com.liu.spring.register.RegisterWare;

public abstract class AbstractXmlReader implements XmlReader {

	private RegisterWare registerWare;

	private Reflex reflex;
     
	private ParserContext parserContext;
	
	private BeanFactory beanFactory;
	
	public AbstractXmlReader(BeanFactory beanFactory) {
         
		this.parserContext = new ParserContext();
		this.beanFactory = beanFactory;
		
		
	}

	public <T> void loadBeanDefinition(Node<T> e2) {
		
		String name = this.getElementName(e2);

		DefaultBeanFactory defaultBeanFactory = (DefaultBeanFactory) beanFactory;

		XmlParser xmlParser = defaultBeanFactory.getSpaceHandlerSupport(name);
		
		if (xmlParser != null) {
			
			GenericBeanDefinition genericBeanDefinition = xmlParser.parserElement(e2,parserContext);
		   
			if (genericBeanDefinition!=null) {
				
				defaultBeanFactory.addCount(registerWare.registBention(defaultBeanFactory, genericBeanDefinition));
				
			}
			
		} else {

			
		}

	}

	private <T> String getElementName(Node<T> e2) {
		String name = null;
		if (e2.getNode() instanceof Element) {

			Element e = (Element) e2.getNode();
			name = e.getName();

		}

		return name;
	}

	public abstract BeanDefinition CrateBeanDefinitionObject();

	/**
	 * 返回一个节点集合 beans里面的所有元素节点
	 * 
	 * @param re
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public abstract List<Node> getListElement(Resource re);

	@SuppressWarnings("unchecked")
	public void loadBeanDefinition(Resource re) {
		initParseContext();
		List<Node> listElement = this.getListElement(re);
		/**
		 * 校验
		 * 
		 */

		for (Node no : listElement) {

			loadBeanDefinition(no);
			
		}

	}
     public void initParseContext() {
    	 
    	 parserContext.setBeanFactory(this.beanFactory);
    	 
 		 parserContext.setRegisterWare(this.registerWare);
    	
    }

	public RegisterWare getRegisterWare() {
		return registerWare;
	}

	public void setRegisterWare(RegisterWare registerWare) {
		this.registerWare = registerWare;
	}

	public Reflex getReflex() {
		return reflex;
	}

	public void setReflex(Reflex reflex) {
		this.reflex = reflex;
	}
	

}
