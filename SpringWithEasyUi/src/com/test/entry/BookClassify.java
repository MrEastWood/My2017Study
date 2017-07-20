package com.test.entry;

import java.util.Date;

public class BookClassify {
	
	private String classifyID;
	private String classifyName;
	private String description;
	private Date createDate;
	private Date lastModifyDate;
	
	public String getClassifyID() {
		return classifyID;
	}
	public void setClassifyID(String classifyID) {
		this.classifyID = classifyID;
	}
	
	public String getClassifyName() {
		return classifyName;
	}
	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getLastModifyDate() {
		return lastModifyDate;
	}
	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

}
