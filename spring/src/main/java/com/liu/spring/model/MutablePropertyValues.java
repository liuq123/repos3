package com.liu.spring.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MutablePropertyValues implements PropertyValues {
	private List<String> propertyName;
	private Map<String, Object> valueMap;
	private List<String> refPropertyName;

	public MutablePropertyValues() {
		super();
		this.propertyName = new ArrayList<String>();
		this.valueMap = new HashMap<String, Object>();
		refPropertyName = new ArrayList<String>();
	}

	public List<String> getPropertyName() {
		return propertyName;
	}

	public Map<String, Object> getValueMap() {
		return valueMap;
	}

	public void addProName(String name) {

		this.propertyName.add(name);
	}

	public void addValue(String name, String value) {

		this.valueMap.put(name, value);
	}

	public void addRefName(String name) {

		this.refPropertyName.add(name);
	}

	public boolean isContainRefName(String name) {

		return this.refPropertyName.contains(name);
	}

	public List<String> getRefPropertyName() {
		return refPropertyName;
	}

}
