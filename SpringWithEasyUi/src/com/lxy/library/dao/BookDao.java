package com.lxy.library.dao;

import org.springframework.stereotype.Repository;

import com.lxy.library.entry.Book;

@Repository("bookDao")
public class BookDao extends BaseDao<Book> {

}
