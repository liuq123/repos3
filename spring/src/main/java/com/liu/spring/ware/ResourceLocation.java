package com.liu.spring.ware;

import com.liu.spring.model.Resource;

public interface ResourceLocation {
	/**
	 * 把路径资源封装成resource对象
	 * 
	 * @param locations
	 * @return
	 */
	Resource resolve(String locations);
	
	
}
