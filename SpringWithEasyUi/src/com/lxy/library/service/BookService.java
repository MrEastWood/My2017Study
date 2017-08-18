package com.lxy.library.service;

import java.util.List;
import java.util.Map;

import com.lxy.library.common.Pager;
import com.lxy.library.entry.Book;

public interface BookService {

	public void addBook(Book book) throws Exception ;
	
	//public List<Book> listClassify();
	
	public Pager<Book> queryBookPage(int pageNumber,int pageSize,Map<String,String> parms);
	
	public Book loadBookForm(String id);
	
	public void editBook(Book book) throws Exception ;
	
	public void deleteBook(Book book)throws Exception ;
}
