package com.test.service;

import java.util.List;

import com.test.common.Pager;
import com.test.entry.Reader;

public interface ReaderService {

	public void addReader(Reader reader) throws Exception ;
	
	public List<Reader> listReader();
	
	public Pager<Reader> queryReaderPage(int pageNumber,int pageSize,String ReaderName);
	
	public void modifyReader(Reader reader) throws Exception ;
	
	public void deleteReader(Reader reader);
	
	public Reader findById(String readerId) throws Exception ;
}
