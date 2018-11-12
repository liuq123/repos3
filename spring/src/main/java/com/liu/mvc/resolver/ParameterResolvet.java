package com.liu.mvc.resolver;

import javax.servlet.http.HttpServletRequest;

import com.liu.mvc.beans.HandlerMethod;
import com.liu.mvc.beans.RquestCheckInfo;

public interface ParameterResolvet {
	RquestCheckInfo	checkRequst(HttpServletRequest request,HandlerMethod method);
}
