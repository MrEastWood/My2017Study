package com.test.service;

import java.util.List;
import java.util.Map;

import com.test.common.Pager;
import com.test.entry.Book;
import com.test.entry.BrrowNow;

public interface BrrowService {

	public void brrowBook(String readerId,String bookId) throws Exception ;
	
	public List<BrrowNow> queryInBrrow(String readerId);
}
