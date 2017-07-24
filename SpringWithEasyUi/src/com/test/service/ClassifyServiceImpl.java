package com.test.service;

import java.util.ArrayList;
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
import com.test.entry.BookClassify;

@Service("classifyService")
@Transactional(rollbackFor=Exception.class)
public class ClassifyServiceImpl implements ClassifyService {

	@Autowired
	protected BaseDao<BookClassify> classifyDao;
	
	@Override
	public void addClassify(BookClassify classify) throws Exception{
		// TODO Auto-generated method stub
		//检查是否已存在同名的类型
		List<Criterion> cList = new ArrayList<Criterion>();
		cList.add(Restrictions.eq("classifyName", classify.getClassifyName()));
		Long count = classifyDao.getRowCount(BookClassify.class, cList);
		if(count > 0){
			BusException exception = new BusException("分类名重复");
			exception.setReturnCode("A001");
			throw exception;
		}
		//插入书籍分类
		classifyDao.insertRecord(classify);
	}

	@Override
	public List<BookClassify> listClassify() {
		// TODO Auto-generated method stub
		return classifyDao.getRecordList(BookClassify.class, new ArrayList<Criterion>());
		
		
	}

	@Override
	public Pager<BookClassify> queryClassifyPage(int pageNumber, int pageSize, String classifyName) {
		// TODO Auto-generated method stub
		//设置分页信息
		Pager<BookClassify> classifyPage = new Pager<BookClassify>();
		classifyPage.setPageNumber(pageNumber);
		classifyPage.setPageSize(pageSize);
		
		//设置查询条件
		List<Criterion> cList = new ArrayList<Criterion>();
		if(classifyName != null && !classifyName.trim().isEmpty()){
			cList.add(Restrictions.like("classifyName", classifyName,MatchMode.ANYWHERE));
		}
		return classifyDao.getPageRecords(BookClassify.class,cList, classifyPage);
	}
	
	
	
	

}
