package com.liu.mvc.resolver;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.liu.mvc.beans.HandlerMethod;
import com.liu.mvc.beans.ResponseType;
import com.liu.mvc.beans.RquestCheckInfo;
import com.liu.spring.reflex.Reflex;
import com.liu.spring.reflex.ReflexImpl;
import com.liu.spring.util.GetListFactory;

public class HttpRequestParameterResolvet implements ParameterResolvet {
	 private Reflex reflex;
	 
	 
	public HttpRequestParameterResolvet() {
	    this.reflex = new ReflexImpl();
	}


	/**
	 * 校验参数类型、值、请求方式
	 */
	@Override
	public RquestCheckInfo checkRequst(HttpServletRequest request, HandlerMethod method) {
		RquestCheckInfo check = new RquestCheckInfo();
		List<Object> parValue = GetListFactory.buildArrayList();
		Map<String, Class<?>> parameterNameAndType = method.getParameterNameAndType();
		/**
		 * 方法返回值类型
		 */
	   Type returnType = method.getMethod().getAnnotatedReturnType().getType();
		
		for (String parName:method.getParameterName()) {
			
			Class<?> type = parameterNameAndType.get(parName);
			/**
			 * 是不是基本类型
			 */
		       if (reflex.isBaseType(type)) {  
		    	   
		    	      String parameterValue = request.getParameter(parName);
		    	
		    		   parValue.add( reflex.typeChange(type, parameterValue));
			    	   check.setParameterNamAndValue(parName, reflex.typeChange(type, parameterValue));
			    	    
		       }else if (type==String.class) {
		    	
		    	   parValue.add( request.getParameter(parName));
		    	   check.setParameterNamAndValue(parName, request.getParameter(parName));
		    	   
		    	   
		    	  } else {
		    		  
		    		  
		    	 }
		     
			
			
			
		}
		
		check.setExceptionMessage(null);
		check.setMethodReturnType(returnType);
		check.setResponseType(ResponseType.JSON);
		check.setParameterValues(parValue);
		check.setParameterCondition(true);
		return check ;		
	}

	
	
}
