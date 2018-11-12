package com.liu.mvc.resolver;


import javax.servlet.http.HttpServletResponse;

import com.liu.mvc.beans.View;


public interface ViewResolver {
	
	void resolveView(View view, HttpServletResponse response) throws Exception;
	
	
}
