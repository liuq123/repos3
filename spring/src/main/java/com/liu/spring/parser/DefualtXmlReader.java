package com.liu.spring.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.liu.spring.factory.BeanFactory;
import com.liu.spring.model.BeanDefinition;
import com.liu.spring.model.GenericBeanDefinition;
import com.liu.spring.model.MutablePropertyValues;
import com.liu.spring.model.Node;
import com.liu.spring.model.Resource;
import com.liu.spring.reflex.Reflex;

public class DefualtXmlReader extends AbstractXmlReader {

	public DefualtXmlReader(BeanFactory beanFactory) {
		  super(beanFactory);
	}
	
	public Element getRootElement(Resource re) {

		SAXReader reader = new SAXReader();
		Element root = null;

		try {
			Document doc = reader.read(new File(re.getLocations()));
			root = doc.getRootElement();

		} catch (DocumentException e) {

			e.printStackTrace();
		}

		return root;
	}

	@Override
	public BeanDefinition CrateBeanDefinitionObject() {

		return new GenericBeanDefinition();
	}

	@Override
	public List<Node> getListElement(Resource re) {
		Element rootElement = this.getRootElement(re);

		Iterator<Node<Element>> iterator = rootElement.elementIterator();

		List<Node> lie = new ArrayList<Node>();

		while (iterator.hasNext()) {

			lie.add(new Node(iterator.next()));
		}

		return lie;
	}

	public <T> void checkElement(Reflex reflex, List<Node> lie) {
		/**
		 * ≈–∂œid «∑Ò÷ÿ∏¥
		 */
		for (Node el : lie) {

			Element e = (Element) el;

		}

	}
	
	

}
