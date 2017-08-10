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
public class BrrowNow {
	
	@Id
	private String journal;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "readerId")
	private Reader reader;
	
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "bookId")
	private Book book;
	
	@Temporal(TemporalType.DATE)
	private Date brrowDate;
	
	@Temporal(TemporalType.DATE)
	private Date expDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date txnDate;

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

	public Date getBrrowDate() {
		return brrowDate;
	}

	public void setBrrowDate(Date brrowDate) {
		this.brrowDate = brrowDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public Date getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}
	
}