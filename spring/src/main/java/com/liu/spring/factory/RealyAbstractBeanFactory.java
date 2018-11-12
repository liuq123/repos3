package com.liu.spring.factory;

import java.util.HashMap;
import java.util.Map;

public class RealyAbstractBeanFactory extends AbstractBeanFactory {
	
    private  final  Map<String,Object> rentyMap = new HashMap<String,Object>();

	public Map<String, Object> getRentyMap() {
		
		return rentyMap;
		
	}
      public void addObj(String name,Object ob) {      
    	  
    	  rentyMap.put(name, ob);
      }

    
    
    
}

