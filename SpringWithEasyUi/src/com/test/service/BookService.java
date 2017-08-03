package com.test.service;

import java.util.List;
import java.util.Map;

import com.test.common.Pager;
import com.test.entry.Book;

public interface BookService {

	public void addBook(Book book) throws Exception ;
	
	//public List<Book> listClassify();
	
	public Pager<Book> queryBookPage(int pageNumber,int pageSize,Map<String,String> parms);
	
	public Book loadBookForm(String id);
	//public void modifyClassify(Book book) throws Exception ;
	
	//public void deleteClassify(Book book);
}
