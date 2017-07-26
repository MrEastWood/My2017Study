package com.test.service;

import java.util.List;

import com.test.common.Pager;
import com.test.entry.Book;

public interface BookService {

	public void addBook(Book book) throws Exception ;
	
	//public List<Book> listClassify();
	
	//public Pager<Book> queryClassifyPage(int pageNumber,int pageSize,String classifyName);
	
	//public void modifyClassify(Book book) throws Exception ;
	
	//public void deleteClassify(Book book);
}
