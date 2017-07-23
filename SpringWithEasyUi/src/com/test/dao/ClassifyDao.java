package com.test.dao;

import org.springframework.stereotype.Repository;

import com.test.entry.BookClassify;

@Repository("classifyDao")
public class ClassifyDao extends BaseDao<BookClassify> {

}
