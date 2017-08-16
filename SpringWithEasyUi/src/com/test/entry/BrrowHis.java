package com.test.entry;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class BrrowHis {
	
	@Id
	private String journal;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "readerId")
	private Reader reader;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "bookId")
	private Book book;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date brrowTxnTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date returnTxnTime;

	public String getJournal() {
		return journal;
	}

	public void setJournal(String journal) {
		this.journal = journal;
	}

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Date getBrrowTxnTime() {
		return brrowTxnTime;
	}

	public void setBrrowTxnTime(Date brrowTxnTime) {
		this.brrowTxnTime = brrowTxnTime;
	}

	public Date getReturnTxnTime() {
		return returnTxnTime;
	}

	public void setReturnTxnTime(Date returnTxnTime) {
		this.returnTxnTime = returnTxnTime;
	}
	
	

}