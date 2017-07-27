package com.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.common.BusException;
import com.test.common.Pager;
import com.test.dao.BaseDao;
import com.test.entry.Book;
import com.test.entry.BookClassify;

@Service("bookService")
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl implements BookService {

	@Autowired
	protected BaseDao<Book> bookDao;
	@Autowired
	protected BaseDao<BookClassify> classifyDao;

	@Override
	public void addBook(Book book) throws Exception {

		// 检查是否已存在重复的id
		Book old = bookDao.getRecordById(Book.class, book.getBookId());
		if(old != null){
			BusException e = new BusException("书籍编号重复");
			e.setReturnCode("B001");
			throw e;
		}
		
		// 检查分类是否存在
		BookClassify cls = classifyDao.getRecordById(BookClassify.class, book.getBookClassify().getClassifyId());
		if(cls == null){
			BusException e = new BusException("书籍分类不存在");
			e.setReturnCode("B002");
			throw e;
		}
		bookDao.insertRecord(book);
	}
}
