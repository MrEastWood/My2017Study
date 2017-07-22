package com.test.entry;

import java.util.List;

public class Pager<T> {
	//每页记录数
	private int pageSize;
	//当前页
	private int pageNo;
	//记录总数
	private int totalRecs;
	//记录数据
	private List<T> recordList;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getTotRecs() {
		return totalRecs;
	}
	public void setTotRecs(int totRecs) {
		this.totalRecs = totRecs;
	}
	
	public List<T> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<T> recordList) {
		this.recordList = recordList;
	}
	
}
