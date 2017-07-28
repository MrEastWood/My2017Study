package com.test.firstclass;

import java.io.Serializable;

public class MaxId implements Serializable {
	
	private String idType;
	
	private String idPrefix;
	
	private int curValue;

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdPrefix() {
		return idPrefix;
	}

	public void setIdPrefix(String idPrefix) {
		this.idPrefix = idPrefix;
	}

	public int getCurValue() {
		return curValue;
	}

	public void setCurValue(int curValue) {
		this.curValue = curValue;
	}
	
	public String toString(){
		String str = String.format("%07d", curValue);
		return this.idType + this.idPrefix + str;
	}
}
