package com.test.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ReturnMessage {
	
	/**
	 * 返回信息类型 :  N - normal  E - Error
	 */
	private boolean success;

	/**
	 * 返回的信息码，预定长度为4位，信息码为空表示成功
	 */
	private String returnCode;
	
	/**
	 * 返回的错误信息，只有错误时才返回
	 */
	private String messageData;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public String getReturnCode() {
		return returnCode;
	}
	
	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}
	
	public String getMessageData() {
		return messageData;
	}
	
	
	public void setMessageData(String messageData) {
		this.messageData = messageData;
	}
	
	
	/* (non-Javadoc)
	 * 重写toString方法，格式化为json字符串
	 */
	public String toString(){
		//后面可以考虑在JavaConfig中，建立一个工厂方法产生Gson对象，通过spring注入
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}
	
}
