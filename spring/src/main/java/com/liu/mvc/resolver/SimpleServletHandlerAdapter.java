package com.liu.mvc.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liu.mvc.beans.ModelAndView;

public class SimpleServletHandlerAdapter implements HandlerAdapter {

	@Override
	public boolean supports(Object handler) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getLastModified(HttpServletRequest request, Object handler) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
