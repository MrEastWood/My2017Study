package com.lxy.library.entry;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.google.gson.annotations.Expose;


@Entity
public class BookClassify implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id  
    @GeneratedValue(strategy = GenerationType.AUTO) 
	@Expose
	private int classifyId;
	
	@Expose
	@Column(length=100)
	private String classifyName;
	
	@Expose
	@Column(length=1024)
	private String description;
	
	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Expose
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifyDate;
	
	@OneToMany(fetch = FetchType.LAZY,mappedBy = "bookClassify")
	private Set<Book> bookSet = new HashSet<Book>();
	
	public int getClassifyId() {
		return classifyId;
	}
	public void setClassifyId(int classifyId) {
		this.classifyId = classifyId;
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
