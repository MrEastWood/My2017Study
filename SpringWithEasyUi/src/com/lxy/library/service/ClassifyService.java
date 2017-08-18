package com.lxy.library.service;

import java.util.List;

import com.lxy.library.common.Pager;
import com.lxy.library.entry.BookClassify;

public interface ClassifyService {

	public void addClassify(BookClassify classify) throws Exception ;
	
	public List<BookClassify> listClassify();
	
	public Pager<BookClassify> queryClassifyPage(int pageNumber,int pageSize,String classifyName);
	
	public void modifyClassify(BookClassify classify) throws Exception ;
	
	public void deleteClassify(BookClassify classify);
}
