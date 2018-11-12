package com.liu.spring.util;

public class StringUtil {

	public static String toLowerCaseFirstOne(String s) {
		// 首字母转小写
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	// 首字母转大写
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}
	/**
	 * 返回类名 首字母是小写
	 * @param clssName
	 * @return
	 */
     public static String getLowerCaseFirstOneBeanName(String clssName) {
 			
 		  String e = clssName.substring(clssName.lastIndexOf(".")+1, clssName.length());
 		
            return toLowerCaseFirstOne(e);
     }
     /**
 	 * 判断是否为空
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
