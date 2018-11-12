package com.liu.mvc.beans;

public interface View {
	
	 ResponseType getResponseType();
	 void setResponseType(ResponseType responseType);
	 void setReturnVal(String val);
}
