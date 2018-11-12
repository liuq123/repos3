package com.liu.spring.model;

import java.util.ArrayList;
import java.util.List;

public class ConstructorArgumentValues implements PropertyValues{
      	private List<String> constructor;
      	
      	private List<String>  values;

		public ConstructorArgumentValues() {
		
			this.constructor = new ArrayList<String>();
			
			this.values = new ArrayList<String>();
		}
		
       public void addConstructor(String name) {	
    	  
    	  this.constructor.add(name);
      }
      	
       public void addValue(String value) {	
     	  
     	  this.values.add( value);
       }

	public List<String> getConstructor() {
		return constructor;
	}

	public List<String> getValues() {
		return values;
	}


       
}
