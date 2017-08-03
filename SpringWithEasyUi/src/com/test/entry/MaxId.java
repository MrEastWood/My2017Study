package com.test.entry;

import javax.persistence.Column;
import javax.persistence.Id;

public class MaxId {
	
	@Id
	private String idType;
	
	@Column
	private String idPrefix;
	
	@Column
	private int idSeqNum;

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

	public int getidSeqNum() {
		return idSeqNum;
	}

	public void setidSeqNum(int idSeqNum) {
		this.idSeqNum = idSeqNum;
	}
	
	public String toString(){
		return this.idPrefix + String.format("%07d", this.idSeqNum);
	}
	
}
