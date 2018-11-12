package com.liu.mvc.beans;

import java.util.HashMap;
import java.util.Map;

/**
 * servlet ±Í«© Ù–‘
 */
public class ServletProperty extends ServletConfigPropertyValues{
	
		private String servletName;
		
		private String servletClass;
		
		private int loadOnStartup;
		/**
		 * param-name
		 */
		private Map<String,String> param = new HashMap<String,String>();
		
		/**
		 * servlet-mapping
		 * key:servlet-name
		 * value:url-pattern
		 */
		private Map<String,String> mapping = new HashMap<String,String>();

		public String getServletName() {
			return servletName;
		}

		public void setServletName(String servletName) {
			this.servletName = servletName;
		}

		public String getServletClass() {
			return servletClass;
		}

		public void setServletClass(String servletClass) {
			this.servletClass = servletClass;
		}

		public int getLoadOnStartup() {
			return loadOnStartup;
		}

		public void setLoadOnStartup(int loadOnStartup) {
			this.loadOnStartup = loadOnStartup;
		}

		public Map<String, String> getParam() {
			return param;
		}

		public void setParam(Map<String, String> param) {
			this.param = param;
		}

		public Map<String, String> getMapping() {
			return mapping;
		}

		public void setMapping(Map<String, String> mapping) {
			this.mapping = mapping;
		}

		@Override
		public String toString() {
			return "ServletSource [servletName=" + servletName + ", servletClass=" + servletClass + ", loadOnStartup="
					+ loadOnStartup + ", param=" + param + ", mapping=" + mapping + "]";
		}
		
		
		
		
		
}
