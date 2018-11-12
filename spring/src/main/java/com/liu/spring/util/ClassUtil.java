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
	 * ��ȡ����ָ��ע�����
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
	 * ��ȡָ�������µ�������
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
	// ��һ���࣬��������ӿڵ�����ʵ����

	public static List<Class<?>> getAllClass(Class<?> c) {
		// ���а���
		List<String> names = getAllPageNames();

		List<Class<?>> returnClassList = new ArrayList<Class<?>>(); // ���ؽ��

		// String packageName = c.getPackage().getName(); // ��õ�ǰ�İ���

		try {
			for (String pageName : names) {
				List<Class<?>> allClass = getClasses(pageName); // ��õ�ǰ�����Լ��Ӱ��µ�������
				// �ж��Ƿ���ͬһ���ӿ�

				for (int i = 0; i < allClass.size(); i++) {

					if (c.isAssignableFrom((Class<?>) allClass.get(i))) { // �ж��ǲ���һ���ӿ�

						if (!c.equals(allClass.get(i))) { // �����ӽ�ȥ

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

	// ��һ�����в��ҳ����е��࣬��jar���в��ܲ���

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
	 * ��ǰ����ִ��·��
	 * @return
	 */
	public static File getCurrentCatalog() {
		File root = new File(ClassUtil.class.getResource("").getPath());
	     
		/**
		 * ��ȡclasseesǰ��·��
		 */
		String path = root.getPath();
		
		path = path.substring(0, path.indexOf("classes"))+"/classes/";
	    root = new File(path);
		
		return root;
	}
	/**
	 * ��ȡclass�µİ��� ������lib������İ���
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
	 * ��ȡ������������� ָ��ע��
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
	 * ��ȡָ��ע��ķ���
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
	 * ��ȡ���������ƺ�����
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
			// ʹ��javaassist�ķ��䷽����ȡ�����Ĳ�����
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
				
				// paramNames��������
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
			// ʹ��javaassist�ķ��䷽����ȡ�����Ĳ�����
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
				
				// paramNames��������
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return parameterName;
	}

	/**
	 * ������������м�ֵ
	 * 
	 * @param object ��������
	 * 
	 */
	public static Map<String, Object> getKeyAndValue(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		// �õ������
		Class<?> userCla = (Class<?>) obj.getClass();
		/* �õ����е��������Լ��� */
		List<Field> fss = ClassUtil.getAllField(userCla);
		for (Field fs:fss) {
			Field f = fs;
			f.setAccessible(true); // ����Щ�����ǿ��Է��ʵ�
			Object val = new Object();
			try {
				val = f.get(obj);
				// �õ������Ե�ֵ
				map.put(f.getName(), val);// ���ü�ֵ
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return map;
	}

	/**
	 * ���������ĳ������ֵ
	 * 
	 * @param object ����
	 * 
	 * @param key    ��
	 * 
	 * @return Object ���ڶ���������Ӧ��ֵ û�в鵽ʱ���ؿ��ַ���
	 */
	public static Object getValueByKey(Object obj, String key) {
		// �õ������
		Class<?> userCla = (Class<?>) obj.getClass();
		/* �õ����е��������Լ��� */
		Field[] fs = userCla.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			Field f = fs[i];
			f.setAccessible(true); // ����Щ�����ǿ��Է��ʵ�
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
		// û�в鵽ʱ���ؿ��ַ���
		return "";
	}

	/**
	 * ������б���������м�ֵ
	 * 
	 * @param object
	 * @return List<Map<String,Object>> �б������ж�������м�ֵ ex:[{pjzyfy=0.00, xh=01,
	 * 
	 */
	public static List<Map<String, Object>> getKeysAndValues(List<Object> object) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (Object obj : object) {
			Class<?> userCla;
			// �õ������
			userCla = (Class<?>) obj.getClass();
			/* �õ����е��������Լ��� */
			Field[] fs = userCla.getDeclaredFields();
			Map<String, Object> listChild = new HashMap<String, Object>();
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true); // ����Щ�����ǿ��Է��ʵ�
				Object val = new Object();
				try {
					val = f.get(obj);
					// �õ������Ե�ֵ
					listChild.put(f.getName(), val);// ���ü�ֵ
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			list.add(listChild);// ��map���뵽list������
		}

		return list;
	}

	/**
	 * ������б������ĳ������ֵ
	 * 
	 */
	public static List<Object> getValuesByKey(List<Object> object, String key) {
		List<Object> list = new ArrayList<Object>();
		for (Object obj : object) {
			// �õ������
			Class<?> userCla = (Class<?>) obj.getClass();
			/* �õ����е��������Լ��� */
			Field[] fs = userCla.getDeclaredFields();
			for (int i = 0; i < fs.length; i++) {
				Field f = fs[i];
				f.setAccessible(true); // ����Щ�����ǿ��Է��ʵ�
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
	 * ��ȡ������������������
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
	  ��ȡ������������
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
	 * ��ȡ��������и���
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
