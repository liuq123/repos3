package com.liu.spring.ware;

import java.io.File;
import java.io.IOException;

import com.liu.spring.model.Resource;
import com.liu.spring.util.ClassUtil;

public class XmlResourceLocation implements ResourceLocation {

	public Resource resolve(String locations) {
		
		Resource resource = null;
		String lo = ClassUtil.getCurrentCatalog().getPath()+"/"+ locations;
		    File fi = new File(lo);
		    String canonicalPath = null;
			try {
			     canonicalPath = fi.getCanonicalPath();
			     fi  = new File(canonicalPath);
			} catch (IOException e) {
			
				e.printStackTrace();
			}
			if (fi.exists()) {
				
				resource = new Resource();
				resource.setFileName(locations);
				resource.setLocations( canonicalPath);
				
			} else {
				System.out.println("当前路径不存在");
			}
		

		return resource;
	}

}
