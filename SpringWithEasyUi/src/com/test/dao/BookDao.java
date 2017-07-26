package com.test.dao;

import org.springframework.stereotype.Repository;

import com.test.entry.Book;

@Repository("bookDao")
public class BookDao extends BaseDao<Book> {

}
