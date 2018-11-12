package com.liu.spring.reflex;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.model.Studens;
import com.liu.spring.util.ClassUtil;

public class ReflexImpl implements Reflex {

	public Object newInstance(String className) {
		try {

			Class<?> forName = Class.forName(className);

			return forName.newInstance();

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		}
		return null;
	}

	public  void assignment(Object obj, Object value, String name) {
		
		try {
			List<Field> allField = ClassUtil.getAllField(obj.getClass());
			Field f = null;
			for (Field fs:allField) {
				if (name.equals(fs.getName())) {
					f = fs;
				}
			}

			Class<?> className = f.getType();
			f.setAccessible(true);
			if (this.isBaseType(className)) {
				/**
				 * 基本类型
				 */
				f.set(obj, typeChange(className, value));

			} else {

				f.set(obj, value);

			}

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Object typeChange(Class<?> className, Object value) {
		
		if ("int".equals(className.toString())) {
			if (value==null) {
				
				return 0;
			}
			return Integer.parseInt((String) value);
		}

		return value;
	}

	public  boolean isBaseType(Class<?> className) {
		
		if (className.equals(java.lang.Integer.class) || "int".equals(className.toString())
				|| className.equals(java.lang.Byte.class) || className.equals(java.lang.Long.class)
				|| className.equals(java.lang.Double.class) || className.equals(java.lang.Float.class)
				|| className.equals(java.lang.Character.class) || className.equals(java.lang.Short.class)
				|| className.equals(java.lang.Boolean.class)) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		ReflexImpl s = new ReflexImpl();
		Studens so = new Studens();
		String ne = "w";
		s.assignment(so, "1", "id");
	}

	public   List<Field> parentClassAssignment(String smallClassName) {
		try {
			
			Class<?> sc = Class.forName(smallClassName);
			Class<?> superclass = sc.getSuperclass();
			Field[] fields = superclass.getDeclaredFields();
			List<Field> type = new ArrayList<Field>();
			for (Field fi : fields) {
				
				type.add(fi);

			}
			return type;
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return null;
	}

	public void assignmentField(Field f, Object value) {
	   
	}

	public void modifyFiledsPower(String fieldName,String   className,Object obj,Object value) {
		 Class<?> cla;
		try {
			cla = Class.forName(className);
			
			Field field = cla.getDeclaredField(fieldName);
			
			field.setAccessible(true);
			
			if (this.isBaseType( field .getType())) {
				/**
				 * 基本类型
				 */
				 field.set(obj, typeChange(cla, value));

			} else {

				 field.set(obj, value);

			}
			
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

	

}
