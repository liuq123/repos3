package com.liu.mvc.utils;

import java.util.List;

public class CollectionUtis {
		 
	public static Object[] listToArray(List<Object> list) {
		 Object[] vals = new Object[list.size()];
		 vals[0]  = "1";
		 vals[1] = "2";
		 for (int i=0;i<list.size();i++) {
			 
			 vals[i] = list.get(i);
			 
		 }
		return vals;
	}
}
