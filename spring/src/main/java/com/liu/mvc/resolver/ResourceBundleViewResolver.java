package com.liu.mvc.resolver;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.liu.mvc.beans.MappingJackson2JsonView;
import com.liu.mvc.beans.View;

public class ResourceBundleViewResolver extends AbstractCachingViewResolver{

	@Override
	public void resolveView(View view, HttpServletResponse response) throws Exception {
		 response.setContentType("text/json;charset=UTF-8");
	        response.setCharacterEncoding("UTF-8");
	        PrintWriter out = response.getWriter();
	        MappingJackson2JsonView vi = (MappingJackson2JsonView) view;
	        String str = vi.getReturnVal();
	        out.println(str);
	        out.flush();
	        out.close();
	
	}

	
}
