package com.liu.spring.register;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.liu.spring.context.ApplicationContext;
import com.liu.spring.factory.BeanFactory;
import com.liu.spring.factory.DefaultBeanFactory;
import com.liu.spring.factory.RealyAbstractBeanFactory;
import com.liu.spring.model.BeanDefinition;
import com.liu.spring.model.GenericBeanDefinition;
import com.liu.spring.reflex.Reflex;
import com.liu.spring.reflex.ReflexImpl;
/**
 * ����ע��ʵ�ֲ��������� ��beanʵ��
 * 
 * @author Administrator
 *
 */
public class SingleRegistImpl implements SingleRegist {

	private Reflex reflex;

	public SingleRegistImpl(ApplicationContext applicationContext) {
		if (applicationContext == null) {

	
			reflex = new ReflexImpl();

		} else {
			
			reflex = new ReflexImpl();

		}

	}

	public Object getBean(String name, BeanFactory beanFactory, BeanFactory realybeanFactory) {
		if (beanFactory instanceof DefaultBeanFactory && realybeanFactory instanceof RealyAbstractBeanFactory) {

			DefaultBeanFactory deful = (DefaultBeanFactory) beanFactory;
			RealyAbstractBeanFactory real = (RealyAbstractBeanFactory) realybeanFactory;

			if (getBean(real, name) != null) {

				return getBean(real, name);
			}

			Map<String, BeanDefinition> beanMap = deful.getBeanMap();
			GenericBeanDefinition gebd = (GenericBeanDefinition) deful.getByName(name);
			
			String className = (String) gebd.getBeanClass();
			Object object = null;
			/**
			 * �ǳ���Ĳ�������ʵ��
			 */
			if (gebd.isAbstractFlag()) {

			} else {
				/**
				 * ����ʵ�����
				 */

				/**
				 * �޲�������
				 */

				if (gebd.getConstructorArgumentValues() == null) {
					object = reflex.newInstance(className);

				} else {

				}
				/**
				 * ��porporty��ֵ
				 */
				Set<Entry<String, Object>> entrySet = gebd.getPropertyValues().getValueMap().entrySet();

				for (Entry<String, Object> si : entrySet) {

					if (gebd.getPropertyValues().isContainRefName(si.getKey())) {
						/**
						 * ��������ȡֵ
						 */
						Object bean = getBean((String) si.getValue(), beanFactory, realybeanFactory);
							
						this.assignment(object, bean, si.getKey());

					} else {

						this.assignment(object, si.getValue(), si.getKey());

					}

				}
				/**
				 * ���ദ��
				 */
				if (gebd.getParentId() != null || "".equals(gebd.getParentId())) {

					String parId = gebd.getParentId();
					
					this.getBean(parId, beanFactory, realybeanFactory);
					
					/**
					 * ע��parent
					 */
				 GenericBeanDefinition gebdParent = (GenericBeanDefinition) deful.getByName(parId );
				
				/**
				 * ��porporty��ֵ
				 */
				Set<Entry<String, Object>> entrySet2 =gebdParent.getPropertyValues().getValueMap().entrySet();

				for (Entry<String, Object> si : entrySet2) {

					if (gebdParent.getPropertyValues().isContainRefName(si.getKey())) {
						/**
						 * ��������ȡֵ
						 */
						Object bean = getBean((String) si.getValue(), beanFactory, realybeanFactory);
						
						reflex.modifyFiledsPower(si.getKey(), (String)gebdParent.getBeanClass(),object,bean);
						 
					
						
					} else {
						reflex.modifyFiledsPower(si.getKey(), (String)gebdParent.getBeanClass(),object,si.getValue());
						
					

					}

				}
				
					}

				}

				/**
				 * ע��ʵ��
				 */
				real.addObj(name, object);
				
			 return object;
		}

		return null;
	}

	/**
	 * �ȴӻ����л�ȡ
	 * 
	 * @param real
	 * @return
	 */
	private Object getBean(RealyAbstractBeanFactory real, String id) {

		return real.getRentyMap().get(id);
	}

	/**
	 * ������ֵ
	 * 
	 * @param obj
	 * @param value
	 * @param fildname
	 */
	public void assignment(Object obj, Object value, String fildname) {

		reflex.assignment(obj, value, fildname);

	}

	public void assignment(Object obj, GenericBeanDefinition gebd) {
		// TODO Auto-generated method stub

	}

	public Object getBean(Class<?> cla, BeanFactory beanFactory, BeanFactory realybeanFactory) {
		if (beanFactory instanceof DefaultBeanFactory && realybeanFactory instanceof RealyAbstractBeanFactory) {

			DefaultBeanFactory deful = (DefaultBeanFactory) beanFactory;

			RealyAbstractBeanFactory real = (RealyAbstractBeanFactory) realybeanFactory;

			Map<Class<?>, String[]> singletonBeanNamesByType = deful.getSingletonBeanNamesByType();

			String[] beanName = singletonBeanNamesByType.get(cla);
			
			 if (singletonBeanNamesByType.get(cla)==null) {
				 
				  return null;
			 }
			 
			return getBean(beanName[0], beanFactory, realybeanFactory);
			
		}
		return null;
	}

}
