package com.lxy.library.entry;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Administrator
 *
 */
@Entity
public class Reader {
	/**
	 * 用户ID
	 */
	@Id
	private String readerId;
	
	/**
	 * 用户名 
	 */
	@Column(length=50,unique=true)
	private String name;
	
	
	/**
	 * 权限   '1' - 可借5本    '2' - 可同时借10本   '3' - 可同时借15本   '4' - 可同时借20本   '5' - 管理员
	 */
	@Column(length=1)
	private String permission;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	
	/**
	 * 'N' - 正常    'F' - 冻结    'E' - 过期
	 */
	@Column(length=1)
	private String status;
	
	@Column(length=1024)
	private String remark;

	public String getReaderId() {
		return readerId;
	}

	public void setReaderId(String readerId) {
		this.readerId = readerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
