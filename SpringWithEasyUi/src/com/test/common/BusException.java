package com.test.common;

public class BusException extends Exception {
	
	private String returnCode;

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
	public BusException(String msg){
		super(msg);
	}
}
