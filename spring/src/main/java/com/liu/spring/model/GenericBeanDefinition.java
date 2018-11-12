package com.liu.spring.model;


public class GenericBeanDefinition implements BeanDefinition {
	
	 private String description;
	 
	 private Resource resource;  
        
	 private String initMethodName;
	 
	 private String destroyMethodName;
	 
	 private volatile Object beanClass;
	 
	 private boolean abstractFlag = false;

	private boolean lazyInit = false;
	 
	 private String beanId;
	 
	 private MutablePropertyValues propertyValues;
	 
	 private MethodOverrides methodOverrides;
	 
	 private ConstructorArgumentValues constructorArgumentValues;
	 
	 private String scope = "";
	 
	 private String parentId; 
	 
	  public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public String getInitMethodName() {
		return initMethodName;
	}

	public void setInitMethodName(String initMethodName) {
		this.initMethodName = initMethodName;
	}

	public String getDestroyMethodName() {
		return destroyMethodName;
	}

	public void setDestroyMethodName(String destroyMethodName) {
		this.destroyMethodName = destroyMethodName;
	}

	public Object getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Object beanClass) {
		this.beanClass = beanClass;
	}

	public boolean isAbstractFlag() {
		return abstractFlag;
	}

	public void setAbstractFlag(boolean abstractFlag) {
		this.abstractFlag = abstractFlag;
	}

	public boolean isLazyInit() {
		return lazyInit;
	}

	public void setLazyInit(boolean lazyInit) {
		this.lazyInit = lazyInit;
	}

	public String getBeanId() {
		return beanId;
	}

	public void setBeanId(String beanId) {
		this.beanId = beanId;
	}

	public MutablePropertyValues getPropertyValues() {
		return propertyValues;
	}

	public void setPropertyValues(MutablePropertyValues propertyValues) {
		this.propertyValues = propertyValues;
	}

	public MethodOverrides getMethodOverrides() {
		return methodOverrides;
	}

	public void setMethodOverrides(MethodOverrides methodOverrides) {
		this.methodOverrides = methodOverrides;
	}

	public ConstructorArgumentValues getConstructorArgumentValues() {
		return constructorArgumentValues;
	}

	public void setConstructorArgumentValues(ConstructorArgumentValues constructorArgumentValues) {
		this.constructorArgumentValues = constructorArgumentValues;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getClassName() {
	
		return (String)this.beanClass;
	}

	
}
