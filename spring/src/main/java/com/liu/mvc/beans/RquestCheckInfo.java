package com.liu.mvc.beans;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RquestCheckInfo {
    /**
     * 方法返回类型
     */
	private Type methodReturnType;
	/**
	 * 请求响应类型
	 */
	private ResponseType responseType;
	/**
	 * 参数值
	 */
	private List<Object> parameterValues = new ArrayList<>();
	
	private Map<String,Object> parameterNamAndValue = new HashMap<>();
	
	/**
	 * 校验情况
	 */
	private boolean parameterCondition = false;
	/**
	 * 异常信息
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
