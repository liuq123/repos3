package com.liu.test;

import javax.servlet.ServletException;

import com.liu.mvc.ancocation.RequestMapping;
import com.liu.mvc.servlet.DispatcherServlet;
import com.liu.mvc.servlet.HttpServletBean;
import com.liu.spring.annocation.Controller;
@Controller
@RequestMapping("/test")
public class MvcTest {
     
	public static void main(String[] args) {
		HttpServletBean he = new DispatcherServlet();
		try {
			he.init();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
