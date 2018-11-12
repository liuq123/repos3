package com.liu.mvc.servlet;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.liu.mvc.context.GenericWebApplicationContext;
import com.liu.mvc.context.WebApplicationContext;
import com.liu.mvc.utils.ApplicationContextUtils;
import com.liu.spring.context.ApplicationContext;
import com.liu.spring.util.ClassUtil;

public abstract class FrameworkServlet extends HttpServletBean {
	/**
	 * �����Ƿ�ˢ��
	 */
	private boolean refreshEventReceived = false;
	/**
	 * �Ƿ񷢲���tomcat��������
	 */
	private boolean publishContext = true;
	/**
	 * springmvc������
	 */
	private WebApplicationContext webApplicationContext;

	/**
	 * ������servlet��WebApplicationContext
	 */
	@Override
	public void initServletBean() {
		/**
		 * ��spring������������������servlet��������
		 */
		this.webApplicationContext = initWebApplicationContext();

	}

	protected void initFrameworkServlet() {
		
	}

	protected WebApplicationContext initWebApplicationContext() {

		ApplicationContext parent = (ApplicationContext) getServletContext()
				.getAttribute(ApplicationContext.CONTEXT_NAME);

		WebApplicationContext wac = null;
		
		if (wac == null) {

			wac = findWebApplicationContext();
		}
		if (wac == null) {
			/**
			 * �Ѵ�����ʽ���Ըĳɷ����в�������
			 */
		 	wac = createWebApplicationContext(parent);
		    GenericWebApplicationContext  s = (GenericWebApplicationContext) wac;
		    s.startContext();
		    wac = s;
		}
		/**
		 * ��ʼ��springmvc����
		 */
		if (!this.refreshEventReceived) {
			
			onRefresh(wac);
		}
		/**
		 * ������tomcat������
		 */
		if (this.publishContext) {
			
			String attrName = getServletContextAttributeName();
			
			getServletContext().setAttribute(attrName, wac);
		
		}

		return wac;
	}
    protected String getServletContextAttributeName() {
    	
    	
    	return WebApplicationContext.SERVLET_CONTEXT_NAME;
    }
    
	protected WebApplicationContext findWebApplicationContext() {

		String attrName = getServletContextAttributeName();

		if (attrName == null) {
			return null;
		}

	 WebApplicationContext wac = (WebApplicationContext)ApplicationContextUtils.getWebApplicationContext(super.getServletContext(), attrName);
   
		return wac;
	}

	protected String getContextAttribute() {

		return ApplicationContext.CONTEXT_NAME;
	}

	protected WebApplicationContext createWebApplicationContext(ApplicationContext parent) {

		Class<?> contextClass = getContextClass();
		
		GenericWebApplicationContext newInstance = (GenericWebApplicationContext) ClassUtil.newInstance(contextClass);
		
		newInstance.setParentContext(parent);
		newInstance.setServletContext(getServletContext());
		
		return newInstance;
		
	}

	protected Class<?> getContextClass() {
		
		return GenericWebApplicationContext.class;
	}
	protected abstract void onRefresh(WebApplicationContext context);
	
	@Override
	protected final void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		processRequest(request, response);
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

	@Override
	protected final void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		processRequest(request, response);
	}
	
	
	
	protected final void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		long startTime = System.currentTimeMillis();
		

	

		try {
			doService(request, response);
		}
		catch (ServletException | IOException ex) {
			ex.printStackTrace();
			throw ex;
		}
		catch (Throwable ex) {
			ex.printStackTrace();
		
		}

		finally {
		
			}
		
	}
	protected abstract void doService(HttpServletRequest request, HttpServletResponse response)
			throws Exception;

}
