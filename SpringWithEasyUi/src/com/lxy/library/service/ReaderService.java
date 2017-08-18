package com.lxy.library.service;

import java.util.List;
import java.util.Map;

import com.lxy.library.common.Pager;
import com.lxy.library.entry.Reader;

public interface ReaderService {

	public void addReader(Reader reader) throws Exception ;
	
	public List<Reader> listReader();
	
	public Pager<Reader> queryReaderPage(int pageNumber,int pageSize,Map<String, String> parms);
	
	public void modifyReader(Reader reader) throws Exception ;
	
	public void deleteReader(String readerId) throws Exception ;
	
	public Reader findById(String readerId) throws Exception ;
}
