package com.liu.mvc.utils;

public class JsonUtil {
		
	public static <T>String ObjTojson(T obj) {
		
		if (obj==null) {
			return "";
		}
		
		return (String) obj;
	}
}
