package com.liu.spring.util;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.liu.spring.annocation.Service;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

public class ClassUtil {
	public static <T> T newInstance(Class<T> cls) {

		try {
			return cls.newInstance();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		}
		return null;
	}

	public static Class<?> forName(String name) {

		try {
			return Class.forName(name);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取包含指定注解的类
	 */
	public static <A extends Annotation> List<Class<?>> annotationClass(String pageName, Class<A> ans) {

		List<Class<?>> anCls = new ArrayList<Class<?>>();
		List<Class<?>> classByPageName = getClassByPageName(pageName);

		for (Class<?> cs : classByPageName) {

			A annotation = cs.getAnnotation(ans);

			if (annotation != null) {

				anCls.add(cs);

			}

		}

		return anCls;
	}

	public static void main(String[] args) {
		List<String> allPageNames = ClassUtil.getAllPageNames();
	
	}

	/**
	 * 获取指定包名下的所有类
	 * 
	 * @param c
	 * 
	 * 
	 * @return
	 */
	public static List<Class<?>> getClassByPageName(String pageName) {

		try {

			return getClasses(pageName);

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return null;
	}
	// 给一个类，返回这个接口的所有实现类

	public static List<Class<?>> getAllClass(Class<?> c) {
		// 所有包名
		List<String> names = getAllPageNames();

		List<Class<?>> returnClassList = new ArrayList<Class<?>>(); // 返回结果

		// String packageName = c.getPackage().getName(); // 获得当前的包名

		try {
			for (String pageName : names) {
				List<Class<?>> allClass = getClasses(pageName); // 获得当前包下以及子包下的所有类
				// 判断是否是同一个接口

				for (int i = 0; i < allClass.size(); i++) {

					if (c.isAssignableFrom((Class<?>) allClass.get(i))) { // 判断是不是一个接口

						if (!c.equals(allClass.get(i))) { // 本身不加进去

							returnClassList.add(allClass.get(i));

						}

					}

				}

			}

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		return returnClassList;

	}

	// 从一个包中查找出所有的类，在jar包中不能查找

	private static List<Class<?>> getClasses(String packageName)

			throws ClassNotFoundException, IOException {

		ClassLoader classLoader = Thread.currentThread()

				.getContextClassLoader();

		String path = packageName.replace('.', '/');

		Enumeration<?> resources = classLoader.getResources(path);

		List<File> dirs = new ArrayList<File>();

		while (resources.hasMoreElements()) {

			URL resource = (URL) resources.nextElement();

			dirs.add(new File(resource.getFile()));

		}

		ArrayList<Class<?>> classes = new ArrayList<Class<?>>();

		for (File directory : dirs) {

			classes.addAll(findClasses(directory, packageName));

		}

		return classes;

	}

	private static List<Class<?>> findClasses(File directory, String packageName)

			throws ClassNotFoundException {

		List<Class<?>> classes = new ArrayList<Class<?>>();

		if (!directory.exists()) {

			return classes;

		}

		File[] files = directory.listFiles();

		for (File file : files) {

			if (file.isDirectory()) {

				assert !file.getName().contains(".");

				classes.addAll(findClasses(file, packageName + "."

						+ file.getName()));

			} else if (file.getName().endsWith(".class")) {

				classes.add(Class.forName(packageName

						+ '.'

						+ file.getName().substring(0,

								file.getName().length() - 6)));

			}

		}

		return classes;

	}
	/**
	 * 当前代码执行路径
	 * @return
	 */
	public static File getCurrentCatalog() {
		File root = new File(ClassUtil.class.getResource("").getPath());
	     
		/**
		 * 截取classees前的路径
		 */
		String path = root.getPath();
		
		path = path.substring(0, path.indexOf("classes"))+"/classes/";
	    root = new File(path);
		
		return root;
	}
	/**
	 * 获取class下的包名 不包含lib包里面的包名
	 * @return
	 */
	public static List<String> getAllPageNames() {

		List<String> pageNames = new ArrayList<String>();
		String packageName = "";
	
		File root = ClassUtil.getCurrentCatalog();
		try {
			loop(root, packageName, pageNames);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return pageNames;

	}
	
	public static void loop(File folder, String packageName, List<String> pageNames) throws Exception {
		File[] files = folder.listFiles();
		for (int fileIndex = 0; fileIndex < files.length; fileIndex++) {
			File file = files[fileIndex];
			if (file.isDirectory()) {

				loop(file, packageName + file.getName() + ".", pageNames);

			} else {
			      if (file.getName().contains(".class")) {
			    	   String listMethodNames = packageName ;
						String pageName = listMethodNames.substring(0, listMethodNames.lastIndexOf("."));
						if (!pageNames.contains(pageName)) {
							
							pageNames.add(pageName);
							
						}
			      }
			}
		}
	}

	/**
	 * 获取类的所有属性名 指定注解
	 * 
	 * @param cl
	 * @return
	 */
	public static <A extends Annotation> List<String> appointAnnotationFieldName(Class<?> clss,
			Class<A> annotationClass) {

		List<String> lis = new ArrayList<String>();
		Field[] fields = clss.getDeclaredFields();

		for (Field fi : fields) {

			A annotation = fi.getAnnotation(annotationClass);

			if (annotation != null) {

				lis.add(fi.getName());

			}

		}

		return lis;
	}

	/**
	 * 获取指定注解的方法
	 * 
	 * @param clss
	 * @param annotationClass
	 * @return
	 */
	public static <A extends Annotation> List<Method> getContainAnnotationMethods(Class<?> clss,
			Class<A> annotationClass) {

		List<Method> methods = GetListFactory.buildArrayList();
		Method[] methods2 = clss.getMethods();

		for (Method me : methods2) {

			if (ClassUtil.MethodIsContainAnnotation(me, annotationClass)) {
				methods.add(me);
			}
		}

		return methods;
	}

	public static <A extends Annotation> boolean isContainAnnotation(Class<?> cls, Class<A> annotationClass) {

		return (cls.getAnnotation(annotationClass)) == null ? false : true;

	}

	public static <A extends Annotation> boolean MethodIsContainAnnotation(Method method, Class<A> annotationClass) {

		return (method.getAnnotation(annotationClass)) == null ? false : true;

	}

	/**
	 * 获取方法的名称和类型
	 * 
	 * @param me
	 * @return
	 */
	public static Map<String, Class<?>> getParameterNameAndvalue(Method me, Class<?> cls) {

		Map<String, Class<?>> parameterNameAndvalue = GetListFactory.buildHashMap();
		Class<?> clazz = cls;
		try {
		
		
			ClassPool pool = ClassPool.getDefault();
			ClassClassPath classPath = new ClassClassPath(ClassUtil.class);
			pool.insertClassPath(classPath);
 
			CtClass cc = pool.get(clazz.getName());
			
			CtMethod cm = cc.getDeclaredMethod(me.getName());
			
			Class<?>[] parameterTypes = me.getParameterTypes();
			// 使用javaassist的反射方法获取方法的参数名
			MethodInfo methodInfo = cm.getMethodInfo();
			CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
			LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
					.getAttribute(LocalVariableAttribute.tag);
			if (attr == null) {
				// exception
			}
			String[] paramNames = new String[cm.getParameterTypes().length];
			int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
			for (int i = 0; i < paramNames.length; i++) {
				paramNames[i] = attr.variableName(i + pos);
				parameterNameAndvalue.put(paramNames[i], parameterTypes[i]);
				
				// paramNames即参数名
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return parameterNameAndvalue;
	}
	public static List<String> getParameterName(Method me, Class<?> cls) {

		List<String> parameterName = GetListFactory.buildArrayList();
		Class<?> clazz = cls;
		try {
		
		
			ClassPool pool = ClassPool.getDefault();
			ClassClassPath classPath = new ClassClassPath(ClassUtil.class);
			pool.insertClassPath(classPath);
 
			CtClass cc = pool.get(clazz.getName());
			
			CtMethod cm = cc.getDeclaredMethod(me.getName());
			
			Class<?>[] parameterTypes = me.getParameterTypes();
			// 使用javaassist的反射方法获取方法的参数名
			MethodInfo methodInfo = cm.getMethodInfo();
			CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
			LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
					.getAttribute(LocalVariableAttribute.tag);
			if (attr == null) {
				// exception
			}
			String[] paramNames = new String[cm.getParameterTypes().length];
			int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
			for (int i = 0; i < paramNames.length; i++) {
				paramNames[i] = attr.variableName(i + pos);
				parameterName.add(paramNames[i]);
				
				// paramNames即参数名
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return parameterName;
	}

	/**
	 * 单个对象的所有键值
	 * 
	 * @param object 单个对象
	 * 
	 */
	public static Map<String, Object> getKeyAndValue(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 得到类对象
		Class<?> userCla = (Class<?>) obj.getClass();
		/* 得到类中的所有属性集合 */
		List<Field> fss = ClassUtil.getAllField(userCla);
		for (Field fs:fss) {
			Field f = fs;
			f.setAccessible(true); // 设置些属性是可以访问的
			Object val = new Object();
			try {
				val = f.get(obj);
				// 得到此属性的值
				map.put(f.getName(), val);// 设置键值
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return map;
	}

	/**
	 * 单个对象的某个键的值
	 * 
	 * @param object 对象
	 * 
	 * @param key    键
	 * 
	 * @return Object 键在对象中所对应得值 没有查到时返回空字符串
	 */
	public static Object getValueByKey(Object obj, String key) {
		// 得到类对象
		Class<?> userCla = (Class<?>) obj.getClass();
		/* 得到类中的所有属性集合 */
		Field[] fs = userCla.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			Field f = fs[i];
			f.setAccessible(true); // 设置些属性是可以访问的
			try {

				if (f.getName().endsWith(key)) {

					return f.get(obj);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		// 没有查到时返回空字符串
		return "";
	}

	/**
	 * 多个（列表）对象的所有键值
	 * 
	 * @param object
	 * @return List<Map<String,Object>> 列表中所有对象的所有键值 ex:[{pjzyfy=0.00, xh=01,
	 * 
	 */
	public static List<Map<String, Object>> getKeysAndValues(List<Object> object) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Object obj : object) {
			Class<?> userCla;
			// 得到类对象
			userCla = (Class<?>) obj.getClass();
			/* 得到类中的所有属性集合 */
			Field[] fs = userCla.getDeclaredFields();
			Map<String, Object> listChild = new HashMap<String, Object>();
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true); // 设置些属性是可以访问的
				Object val = new Object();
				try {
					val = f.get(obj);
					// 得到此属性的值
					listChild.put(f.getName(), val);// 设置键值
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			list.add(listChild);// 将map加入到list集合中
		}

		return list;
	}

	/**
	 * 多个（列表）对象的某个键的值
	 * 
	 */
	public static List<Object> getValuesByKey(List<Object> object, String key) {
		List<Object> list = new ArrayList<Object>();
		for (Object obj : object) {
			// 得到类对象
			Class<?> userCla = (Class<?>) obj.getClass();
			/* 得到类中的所有属性集合 */
			Field[] fs = userCla.getDeclaredFields();
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true); // 设置些属性是可以访问的
				try {
					if (f.getName().endsWith(key)) {
						list.add(f.get(obj));
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 获取所有属性名包括父类
	 * 
	 * @param cls
	 * @return
	 */
	public static List<String> getAllFieldName(Class<?> cls) {

		List<String> allFieldName = GetListFactory.buildArrayList();

		List<Field> allField = ClassUtil.getAllField(cls);

		for (Field fi : allField) {

			allFieldName.add(fi.getName());
		}
		return allFieldName;
	}

	/**
	  获取所有属性属性
	 * 
	 * @return
	 */

	public static List<Field> getAllField(Class<?> cls) {
		
		List<Class<?>> lis = ClassUtil.getItselfAndSuperClass(cls);
		List<Field> fields = GetListFactory.buildArrayList();

		for (Class<?> cl : lis) {
			Field[] declaredFields = cl.getDeclaredFields();

			for (Field fi : declaredFields) {

				fields.add(fi);
			}
		}
		return fields;
	}
	/**
	 * 获取该类和所有父类
	 * @param cls
	 * @return
	 */
	public static List<Class<?>> getItselfAndSuperClass(Class<?> cls) {

		List<Class<?>> lis = GetListFactory.buildArrayList();

		while (cls != null) {
			lis.add(cls);
			cls = cls.getSuperclass();
		}

		return lis;
	}
   
}
