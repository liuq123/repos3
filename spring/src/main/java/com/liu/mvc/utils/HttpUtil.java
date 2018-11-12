package com.liu.mvc.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
	public static String getRelativeUrl(HttpServletRequest processedRequest) {
		///spring/FirsetServlet/index
		String path= processedRequest.getRequestURI();
		///FirsetServlet
		String servletPath = processedRequest.getServletPath();
		String relativeUrl = path.substring(path.indexOf(servletPath)+1, 
				                                         path.length());
		
		return relativeUrl.substring(relativeUrl.indexOf("/"),relativeUrl.length());
	}
}
