package com.liu.spring.util;

public class StringUtil {

	public static String toLowerCaseFirstOne(String s) {
		// ����ĸתСд
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	// ����ĸת��д
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	/**
	 * �������� ����ĸ��Сд
	 * @param clssName
	 * @return
	 */
     public static String getLowerCaseFirstOneBeanName(String clssName) {
 			
 		  String e = clssName.substring(clssName.lastIndexOf(".")+1, clssName.length());
 		
            return toLowerCaseFirstOne(e);
     }
     /**
 	 * �ж��Ƿ�Ϊ��
 	 * 
 	 * @param str
 	 * @return
 	 */
 	public static boolean isNullOrEmpty(Object obj) {

 		if (null == obj || "".equals(obj.toString().trim())
 				|| obj.toString().toUpperCase().equals("NULL")) {
 			return true;
 		}
 		return false;

 	}
}
