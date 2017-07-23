package com.test.common;

import java.util.List;

/**
 * @author Administrator
 * 为了方便分页查询的数据返回，封装一个Pager类型，方便一次性将分页查询的数据返回
 * @param <T> 查询的记录持久化的类型
 */
public class Pager<T> {
	//每页记录数 - 为了适应jquery的datagrid，名称固定
	private int pageSize;
	//当前页 - 为了适应jquery的datagrid，名称固定
	private int pageNumber;
	//记录总数 - 为了适应jquery的datagrid，名称固定
	private Long total;
	//记录数据- 为了适应jquery的datagrid，名称固定
	private List<T> rows;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
}
