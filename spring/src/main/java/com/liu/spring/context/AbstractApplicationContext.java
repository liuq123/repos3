package com.liu.spring.context;

import javax.servlet.ServletContext;

import com.liu.mvc.context.WebApplicationContext;
import com.liu.spring.autowired.AutowiredSupport;
import com.liu.spring.autowired.DefaultAutowiredSupport;
import com.liu.spring.factory.AbstractBeanFactory;
import com.liu.spring.factory.BeanFactory;
import com.liu.spring.factory.DefaultBeanFactory;
import com.liu.spring.factory.RealyAbstractBeanFactory;
import com.liu.spring.model.Resource;
import com.liu.spring.parser.DefualtXmlReader;
import com.liu.spring.parser.DefultNamespaceHandlerResolver;
import com.liu.spring.parser.NamespaceHandlerResolver;
import com.liu.spring.reflex.ReflexImpl;
import com.liu.spring.register.BeanDefinitionRegisterWare;
import com.liu.spring.register.SingleRegist;
import com.liu.spring.register.SingleRegistImpl;
import com.liu.spring.util.ClassUtil;
import com.liu.spring.util.ProdecObjectFactory;
import com.liu.spring.ware.ResourceLocation;
import com.liu.spring.ware.XmlResourceLocation;

public abstract class AbstractApplicationContext implements WebApplicationContext {
	private static final String NamespaceHandlerResolver = null;
	/**
	 * 父类容器
	 */
	private ApplicationContext parentContext;
	/**
	 * 路径解析器
	 */
	private ResourceLocation xmlResourceLocation;
	/**
	 * 配置文件路径
	 */
	private String[] configs;
	/**
	 * beanBeanDefinition工厂
	 */
	private BeanFactory beanFactory;
	/**
	 * 真正的bean对象工厂
	 */
	private BeanFactory realybeanFactory;

	private NamespaceHandlerResolver namespaceHandlerResolver;

	private AutowiredSupport autowiredSupport;

	private ServletContext servletContext;
	// 容器是否启动过
	protected boolean isStart = false;

	public Resource resolve(String locations) {

		return xmlResourceLocation.resolve(locations);
	}

	public void setConfigLocations(String... configLocation) {

		this.configs = new String[configLocation.length];

		for (int i = 0; i < configLocation.length; i++) {

			this.configs[i] = configLocation[i];

		}

	}

	public void refash() {
		if (isStart) {

			return;
		}
		prepare();
		prepareBeanFactory();

		isStart = true;
	}

	public void prepareBeanFactory() {

		BeanFactory beanDefinition = getBeanDefinition();
		initParse(new DefultNamespaceHandlerResolver());
		/**
		 * 开始注册beanDefinition对象
		 */
		loadBeanDefinitions(beanDefinition);

	}

	public void loadBeanDefinitions(BeanFactory beanFactory) {

		ResourceLocation resourceLocation = getResourceLocation();

		Resource[] configResources = getConfigResources(resourceLocation);
		/**
		 * 注册解析器
		 */
		DefualtXmlReader parse = new DefualtXmlReader(beanFactory);
		initReader(parse);

		for (Resource re : configResources) {

			parse.loadBeanDefinition(re);

		}
		System.out.println("注册完成!");
		/**
		 * 带注解的类进行依赖注入
		 * 
		 */
		autowiredSupport.injection(this);


	}

	public void initReader(DefualtXmlReader reader) {

		reader.setReflex(new ReflexImpl());
		reader.setRegisterWare(new BeanDefinitionRegisterWare());

	}

	/**
	 * 初始化一些需要的对象
	 */
	public void prepare() {

		this.beanFactory = this.CreateBeanFactory(DefaultBeanFactory.class);
		this.realybeanFactory = this.CreateBeanFactory(RealyAbstractBeanFactory.class);
		this.xmlResourceLocation = new XmlResourceLocation();
		this.autowiredSupport = new DefaultAutowiredSupport();
		
	}

	public void initParse(NamespaceHandlerResolver namespaceHandlerResolver) {

		namespaceHandlerResolver.loadParser(beanFactory);

	}

	private BeanFactory CreateBeanFactory(Class<?> cls) {
		AbstractBeanFactory fco = (AbstractBeanFactory) ProdecObjectFactory.prodecObject(cls);
		fco.setApplicationContext(this);

		return fco;
	}

	public BeanFactory getBeanDefinition() {

		return this.beanFactory;
	}

	public ResourceLocation getResourceLocation() {

		return this.xmlResourceLocation;
	}

	public Resource[] getConfigResources(ResourceLocation resourceLocation) {

		Resource[] resou = new Resource[this.configs.length];

		for (int i = 0; i < this.configs.length; i++) {

			resou[i] = resourceLocation.resolve(this.configs[i]);

		}

		return resou;
	}

	public Object getBean(String name) {

		return getSingleRegistWare(null).getBean(name, beanFactory, realybeanFactory);

	}

	public <T> T getBean(String name, Class<T> cls) {

		return (T) getSingleRegistWare(null).getBean(cls, beanFactory, realybeanFactory);

	}

	public SingleRegist getSingleRegistWare(ApplicationContext applicationContext) {

		if (applicationContext == null) {

			return new SingleRegistImpl(applicationContext);

		} else {

			return new SingleRegistImpl(applicationContext);
		}

	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public BeanFactory getRealybeanFactory() {
		return realybeanFactory;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void setParentContext(ApplicationContext applicationContext) {

		this.parentContext = applicationContext;

	}

	@Override
	public ApplicationContext getParentApplicationContext() {

		return this.parentContext;
	}

}
