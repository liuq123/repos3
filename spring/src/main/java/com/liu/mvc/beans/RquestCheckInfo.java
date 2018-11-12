package com.liu.mvc.beans;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RquestCheckInfo {
    /**
     * ������������
     */
	private Type methodReturnType;
	/**
	 * ������Ӧ����
	 */
	private ResponseType responseType;
	/**
	 * ����ֵ
	 */
	private List<Object> parameterValues = new ArrayList<>();
	
	private Map<String,Object> parameterNamAndValue = new HashMap<>();
	
	/**
	 * У�����
	 */
	private boolean parameterCondition = false;
	/**
	 * �쳣��Ϣ
	 */
	private String exceptionMessage;
	
	public Type getMethodReturnType() {
		return methodReturnType;
	}
	public void setMethodReturnType(Type methodReturnType) {
		this.methodReturnType = methodReturnType;
	}
	public ResponseType getResponseType() {
		return responseType;
	}
	public void setResponseType(ResponseType responseType) {
		this.responseType = responseType;
	}
	public List<Object> getParameterValues() {
		return parameterValues;
	}
	public void setParameterValues(List<Object> parameterValues) {
		this.parameterValues = parameterValues ;
	}
	public Map<String, Object> getParameterNamAndValue() {
		return parameterNamAndValue;
	}
	public void setParameterNamAndValue(String parameterName,Object parameterValue) {
		
		this.parameterNamAndValue .put(parameterName, parameterValue);
		
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public boolean parameterCondition() {
		return parameterCondition;
	}
	public void setParameterCondition(boolean parameterCondition) {
		this.parameterCondition = parameterCondition;
	}
	@Override
	public String toString() {
		return "RquestCheckInfo [methodReturnType=" + methodReturnType + ", responseType=" + responseType
				+ ", parameterValues=" + parameterValues + ", parameterNamAndValue=" + parameterNamAndValue
				+ ", parameterCondition=" + parameterCondition + ", exceptionMessage=" + exceptionMessage + "]";
	}
	
	
	
	
	
	
}
