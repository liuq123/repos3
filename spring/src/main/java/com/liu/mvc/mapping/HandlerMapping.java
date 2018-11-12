package com.liu.mvc.mapping;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
     public static final String MAPPING_NAME_POST  = "-mapping";
	
     public static final String DEFULT_CONTROLLER_URL = "";
     
	HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;
	
	
	
}
