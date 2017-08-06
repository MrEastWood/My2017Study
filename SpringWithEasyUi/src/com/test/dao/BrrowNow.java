package com.test.dao;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;

public class BrrowNow {
	@Id
	private String journal;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "classifyId")
	private int readerId;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "bookId")
	private String bookId;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date brrowTime;
	
	@Temporal(TemporalType.DATE)
	private Date expectDate;
	
}
