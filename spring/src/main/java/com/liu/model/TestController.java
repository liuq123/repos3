package com.liu.model;

import com.liu.mvc.ancocation.RequestMapping;
import com.liu.mvc.ancocation.RequestMethod;
import com.liu.mvc.ancocation.ResponseBody;
import com.liu.spring.annocation.Controller;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@RequestMapping(value="/hello",method = RequestMethod.GET)
	 public String test(String name,int id) {
		 
		
		return "test";
	}
	
	@RequestMapping(value="/json",method = RequestMethod.GET)
	@ResponseBody
	 public String json(String name,int id) {
		 
		
		return "hello json";
	}
}
