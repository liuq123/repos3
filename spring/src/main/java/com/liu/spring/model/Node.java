package com.liu.spring.model;

public class Node<T> {
      /**
       * beans下面的所有元素
       */
	   private T node  ;
	   
	    public Node(Object node) {
	    	 
	    	this.node = (T) node;
	    }

		public <T> T getNode() {
			
			return (T) this.node;
		}
 
	
}
