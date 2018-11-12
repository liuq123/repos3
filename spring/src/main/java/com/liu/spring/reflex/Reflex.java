package com.liu.spring.reflex;

import java.lang.reflect.Field;
import java.util.List;

public interface Reflex {
	 public Object newInstance(String className);
	 public  void assignment(Object obj, Object value,String name);
     public List<Field> parentClassAssignment(String smallClassName);
     public void assignmentField(Field field,Object obj);
     /**
      * 修改该类的属性访问权限
      * @param cls
      */
     
     public void modifyFiledsPower(String fieldName,String classname,Object obj,Object value );
     public  boolean isBaseType(Class<?> className);
     public Object typeChange(Class<?> className, Object value);
     
}
