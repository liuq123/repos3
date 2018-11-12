package com.liu.spring.parser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.liu.mvc.beans.AnnotationConstant;
import com.liu.spring.annocation.Autowired;
import com.liu.spring.annocation.Service;
import com.liu.spring.factory.BeanFactory;
import com.liu.spring.factory.DefaultBeanFactory;
import com.liu.spring.model.GenericBeanDefinition;
import com.liu.spring.model.MutablePropertyValues;
import com.liu.spring.model.Node;
import com.liu.spring.util.ClassUtil;
import com.liu.spring.util.StringUtil;

/**
 * 假设是扫描注解
 * 
 * @author Administrator
 *
 */
public class IXmlParser implements XmlParser {

	public <T> GenericBeanDefinition parserElement( Node<T> e2, ParserContext parserContext) {
	
		String pageName = "com.liu";

		List<Class<?>> annotationClass = this.getlConstainAnClass(pageName);
	
	    Set<GenericBeanDefinition> generateBeanDefinition = this.generateBeanDefinition(annotationClass);
	   
	   for (GenericBeanDefinition si:generateBeanDefinition) {
		   		   
		   parserContext.getRegisterWare().registBention(parserContext.getBeanFactory(), si);
		   
	   }
	 
	  return  null;
	}
	/**
	 * 获取包含注解的类
	 * @param pageName
	 * @return
	 */
	  private List<Class<?>> getlConstainAnClass(String pageName ) {
		
		  List<Class<?>> annotationClass = new ArrayList<>();
		  annotationClass.addAll(ClassUtil.annotationClass(pageName, AnnotationConstant.SERVICE));
		  annotationClass.addAll(ClassUtil.annotationClass(pageName, AnnotationConstant.Controller));
		  
		return annotationClass;
	
	}
	private Set<GenericBeanDefinition> generateBeanDefinition(List<Class<?>> annotationClass) {

		Set<GenericBeanDefinition> ges = new HashSet<GenericBeanDefinition>();

		for (Class<?> cs : annotationClass) {

			GenericBeanDefinition generateBeanDefinition = this.generateBeanDefinition(cs);
			ges.add(generateBeanDefinition);
		}
		
		return ges;
	}

	private GenericBeanDefinition generateBeanDefinition(Class<?> annotationClas) {

		GenericBeanDefinition ge = new GenericBeanDefinition();
		//获取指定注解的属性名
		List<String> allFieldName = ClassUtil.appointAnnotationFieldName(annotationClas,Autowired.class);
		
		MutablePropertyValues mu = new MutablePropertyValues();
		/**
		 * 属性名默认就是id名称
		 */
	        
	       for (String si:allFieldName) {
	    	   
	    	   mu.getValueMap().put(si, si);
	       }
		  
		 mu.getRefPropertyName().addAll(allFieldName);
		 
		mu.getPropertyName().addAll(allFieldName);

		String cs = annotationClas.getName();

		ge.setBeanClass(annotationClas.getName());
		
		ge.setPropertyValues(mu);
		
		ge.setBeanId(StringUtil.getLowerCaseFirstOneBeanName(annotationClas.getName()));
			
		return ge;
	}

}
