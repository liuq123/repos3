package com.liu.spring.util;

public class ProdecObjectFactory {
			/**
			 * 通过类型创建对象
			 * @param clas
			 * @return
			 */
		public static Object prodecObject(Class<?> clas) {
			
			
			return ClassUtil.newInstance(clas);
		}
}
