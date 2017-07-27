package com.test.entry;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String bookId;

	@Column(length = 100, nullable = false)
	private String bookName;

	@Column(length = 1024)
	private String bookDescription;

	@Column(length = 100)
	private String bookPublish;

	@Column(length = 50)
	private String bookAuthor;

	@Column
	private float bookPrice;

	@Column(length = 100)
	private String bookImageUrl;

	@Column(length = 1)
	// N - 正常     B - 借出中    L - 丢失   D - 已下架      F - 冻结 
	private String bookStatus;

	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "classifyId")
	private BookClassify bookClassify;

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookDescription() {
		return bookDescription;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public String getBookPublish() {
		return bookPublish;
	}

	public void setBookPublish(String bookPublish) {
		this.bookPublish = bookPublish;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public float getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookImageUrl() {
		return bookImageUrl;
	}

	public void setBookImageUrl(String bookImageUrl) {
		this.bookImageUrl = bookImageUrl;
	}

	public String getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(String bookStatus) {
		this.bookStatus = bookStatus;
	}

	public BookClassify getBookClassify() {
		return bookClassify;
	}

	public void setBookClassify(BookClassify bookClassify) {
		this.bookClassify = bookClassify;
	}

}
