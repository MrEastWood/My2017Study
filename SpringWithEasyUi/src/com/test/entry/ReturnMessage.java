package com.test.entry;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ReturnMessage {
	
	private String messageType;
	private String returnCode;
	private String messageData;
	
	public String getMessageType() {
		return messageType;
	}
	
	public void setMessageType(String messageType) {
		this.messageType = messageType;
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
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}
	
}
