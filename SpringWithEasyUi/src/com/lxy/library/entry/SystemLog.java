package com.lxy.library.entry;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class SystemLog {
	
	@Id  
    @GeneratedValue(strategy = GenerationType.AUTO) 
	private Long id;
	
	@Column
	private String userName;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date txnTime;
	
	@Column
	private String function;
	
	@Column(length=1024)
	private String txnData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getTxnTime() {
		return txnTime;
	}

	public void setTxnTime(Date txnTime) {
		this.txnTime = txnTime;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public String getTxnData() {
		return txnData;
	}

	public void setTxnData(String txnData) {
		this.txnData = txnData;
	}
	
}
