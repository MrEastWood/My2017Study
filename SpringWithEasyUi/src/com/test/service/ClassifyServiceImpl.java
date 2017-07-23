package com.test.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.common.Pager;
import com.test.dao.BaseDao;
import com.test.entry.BookClassify;

@Service("classifyService")
@Transactional(rollbackFor=Exception.class)
public class ClassifyServiceImpl implements ClassifyService {

	@Autowired
	protected BaseDao<BookClassify> classifyDao;
	
	@Override
	public int addClassify(BookClassify classify){
		// TODO Auto-generated method stub
		
		classifyDao.insertRecord(classify);
//		try {
//			throw new Exception("测试失败") ;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			throw e;
//		}
		return 0;
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
