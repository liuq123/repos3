package com.liu.mvc.mapping;

import java.util.ArrayList;
import java.util.List;

public class UrlPathHelper {
	/**
	 * �������mapping url
	 */
	private String controllerUrl = "";
	/**
	 * ���maping����������URL ȫ·��
	 * 
	 * @return
	 */
	private final List<String> allUrl = new ArrayList<String>();
	
	public String getControllerUrl() {
		
		return controllerUrl;

	}

	public void setControllerUrl(String controllerUrl) {
		
		this.controllerUrl = controllerUrl;
		
	}

	public List<String> getAllUrl() {
		return allUrl;
	}
	public void addAllUrl(String url) {
		
	     this.allUrl.add(url);	
	
	}
	/**
	 * ͨ������·���ж��Ƿ��ǵ�ǰ���handlemapping
	 * @param url
	 * @return
	 */
	public boolean isInThisHadleMapping(String url) {
			boolean isContain = false;
			for (String si:allUrl) {
				
				String e = si;
				 if (url.equals(e) || url==e) {
					      isContain = true;
					      break;
				 }
				
			}
		
		 return isContain;
	}
}
