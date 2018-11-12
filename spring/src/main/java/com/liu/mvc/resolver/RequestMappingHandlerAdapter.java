package com.liu.mvc.resolver;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liu.mvc.ancocation.ResponseBody;
import com.liu.mvc.beans.HandlerMethod;
import com.liu.mvc.beans.MappingJackson2JsonView;
import com.liu.mvc.beans.ModelAndView;
import com.liu.mvc.beans.ResponseType;
import com.liu.mvc.beans.RquestCheckInfo;
import com.liu.mvc.beans.View;
import com.liu.mvc.mapping.AbstractHandlerMethodMapping;
import com.liu.mvc.mapping.RequestMappingHandlerMapping;
import com.liu.mvc.utils.CollectionUtis;
import com.liu.mvc.utils.HttpUtil;
import com.liu.mvc.utils.JsonUtil;
import com.liu.spring.util.ClassUtil;

public class RequestMappingHandlerAdapter extends AbstractHandlerMethodAdapter{
	
	 private ParameterResolvet prarameterResolvet;
	 
	public RequestMappingHandlerAdapter() {
		
		this.prarameterResolvet = new HttpRequestParameterResolvet();
	}

	@Override
	public boolean supports(Object handler) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		 ModelAndView mo = new ModelAndView();
		 RequestMappingHandlerMapping hs = (RequestMappingHandlerMapping) handler;
		  
		 HandlerMethod method = hs.getHandlerMethodRelativeUrl(HttpUtil.getRelativeUrl(request));
		 
		 /**
		  * 返回校验信息
		  */
		 RquestCheckInfo checkRequst = prarameterResolvet.checkRequst(request, method);
		 /**
		  * 校验成功
		  */
		  if (checkRequst.parameterCondition()) { 
			  //获取对象
			  Object controBean = method.getBeanFactory().getBean("",method.getControllerClass());
			//执行方法
			  Object returnValue = this.getMethodReturnVal(method, controBean, checkRequst.getParameterValues());
			  
			  if (checkRequst.getResponseType() == ResponseType.JSON) {
				  	
				  View view = new MappingJackson2JsonView();
				  
				    view.setResponseType(ResponseType.JSON);
				    view.setReturnVal((String) returnValue);
				    mo.setView(view);
				  
			  } else if (checkRequst.getResponseType() == ResponseType.HEML) {
				  
			  
			  }
			 
	      
			 
		  } else {
			  
			  
		  }
		 
		  
		 
		 /**
		  * 执行方法
		  * 
		  */
		 
		 
		return mo;
	}
	

	private Object getMethodReturnVal(HandlerMethod method,Object controBean,List<Object> parValue) {
		
		 Object value = null;
		
		try {
			Object[] array = parValue.toArray();
			value = method.getMethod().invoke(controBean,  parValue.toArray());
			
		} catch (IllegalAccessException e) {
		
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			
			e.printStackTrace();
		}
		
		return value ;

}
	@Override
	public long getLastModified(HttpServletRequest request, Object handler) {
		// TODO Auto-generated method stub
		return 0;
	}

}
