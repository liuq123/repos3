package com.liu.mvc.support;

import javax.servlet.ServletContext;

import com.liu.mvc.servlet.HttpServletBean;
import com.liu.spring.parser.SourceReader;

public interface ServletReader extends SourceReader{
      
	  public void initServletPrConfigure(HttpServletBean httpServletBean,ServletContext context);
	   
	   
}
