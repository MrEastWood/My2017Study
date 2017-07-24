package com.test.service;

import java.util.List;

import com.test.common.Pager;
import com.test.entry.BookClassify;

public interface ClassifyService {

	public void addClassify(BookClassify classify) throws Exception ;
	public List<BookClassify> listClassify();
	public Pager<BookClassify> queryClassifyPage(int pageNumber,int pageSize,String classifyName);
}
