package com.liu.spring.ware;

import com.liu.spring.model.Resource;

public interface ResourceLocation {
	/**
	 * ��·����Դ��װ��resource����
	 * 
	 * @param locations
	 * @return
	 */
	Resource resolve(String locations);
	
	
}
