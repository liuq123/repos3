package com.liu.spring.util;

public class ProdecObjectFactory {
			/**
			 * ͨ�����ʹ�������
			 * @param clas
			 * @return
			 */
		public static Object prodecObject(Class<?> clas) {
			
			
			return ClassUtil.newInstance(clas);
		}
}
