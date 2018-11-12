package com.liu.mvc.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import com.liu.mvc.beans.ServletProperty;
import com.liu.mvc.support.ServletBeanWrapper;
import com.liu.mvc.support.ServletReader;
import com.liu.mvc.support.ServletWebXmlReade;

public abstract class HttpServletBean extends HttpServlet{
      
	

	@Override
	public void init() throws ServletException {
		/**
		 * ��װservlet����
		 */
		ServletReader reader = new ServletWebXmlReade(new ServletBeanWrapper());
		/**
		 * ��servlet��ǩ���ݷ�װ��dispatcherservlet��
		 */
		reader.initServletPrConfigure(this,super.getServletContext());
		
		
		
		initServletBean();
		
		
		
	}
	public abstract void initServletBean();
	
}
