package com.liu.spring.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GetListFactory {
	public  static  <K, V> Map<K, V> buildHashMap() {
        return new HashMap<K, V>();
	}
	public  static  <K, V> Map<K, V> buildHashMap(int size) {
        return new HashMap<K, V>(size);
	}
	public  static  <K> List<K> buildArrayList() {
        return new ArrayList<K>();
	}
	public  static  <K> List<K> buildArrayList(int size) {
        return new ArrayList<K>(size);
	}
	public  static  <K> Set<K> buildHashSet() {
        return new HashSet<K>();
	}
	
	
}