package com.liu.model;

import java.lang.reflect.Field;

public class Teacher {
	private String name;
	private Studens st;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Studens getSt() {
		return st;
	}

	public void setSt(Studens st) {
		this.st = st;
	}

	@Override
	public String toString() {
		return "Teacher [name=" + name + ", st=" + st + "]";
	}
  public static void main(String[] args) {
	  try {
		Class cls = Class.forName("com.liu.model.Studens");
		Field field = cls.getField("name");
		
		field.setAccessible(true);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (NoSuchFieldException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
