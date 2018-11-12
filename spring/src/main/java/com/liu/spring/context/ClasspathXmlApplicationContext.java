package com.liu.spring.context;

public class ClasspathXmlApplicationContext extends AbstractApplicationContext {

	public ClasspathXmlApplicationContext() {

	}

	public ClasspathXmlApplicationContext(String... locations) {
		this(true, locations);
	}

	public ClasspathXmlApplicationContext(boolean isfalsh, String... locations) {
		super.setConfigLocations(locations);

	}

	public ClasspathXmlApplicationContext(String locations) {

		this(true, new String[] { locations });
	}

	public void startContext() {

		setConfigLocations(getLocation());
		refash();

	}
	
	private String[] getLocation() {

		return new String[] { "spring-bean.xml" };
	}
}
