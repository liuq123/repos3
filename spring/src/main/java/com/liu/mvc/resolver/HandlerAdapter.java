package com.liu.mvc.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liu.mvc.beans.ModelAndView;


public interface HandlerAdapter {
	boolean supports(Object handler);

	

	ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;


	long getLastModified(HttpServletRequest request, Object handler);
}
