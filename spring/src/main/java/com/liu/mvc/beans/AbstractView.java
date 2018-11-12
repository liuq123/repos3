package com.liu.mvc.beans;

public abstract class AbstractView implements View {
          protected ResponseType responseType;

		 @Override
		  public ResponseType getResponseType() {
			
			
			return responseType;
		}
		 @Override
		public void setResponseType(ResponseType responseType) {
			 
			 this.responseType = responseType;
			 
		}
          
          
          
}
