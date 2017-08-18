package com.lxy.library.service;

import java.util.List;
import java.util.Map;

import com.lxy.library.common.Pager;
import com.lxy.library.entry.Book;
import com.lxy.library.entry.BrrowNow;

public interface BrrowService {

	public void brrowBook(String readerId,String bookId) throws Exception ;
	
	public void returnBook(String bookId) throws Exception;
	
	public List<BrrowNow> queryInBrrow(String readerId);
}
