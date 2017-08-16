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
import com.test.dao.ClassifyDao;
import com.test.entry.BookClassify;

@Service("classifyService")
@Transactional(rollbackFor = Exception.class)
public class ClassifyServiceImpl implements ClassifyService {

	@Autowired
	protected ClassifyDao classifyDao;

	@Override
	public void addClassify(BookClassify classify) throws Exception {
		// TODO Auto-generated method stub
		// 检查是否已存在同名的类型
		List<Criterion> cList = new ArrayList<Criterion>();
		cList.add(Restrictions.eq("classifyName", classify.getClassifyName()));
		Long count = classifyDao.getRowCount(BookClassify.class, cList);
		if (count > 0) {
			BusException exception = new BusException("分类名重复");
			exception.setReturnCode("A001");
			throw exception;
		}
		// 插入书籍分类
		classify.setCreateDate(new Date());
		classify.setLastModifyDate(new Date());
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
		// 设置分页信息
		Pager<BookClassify> classifyPage = new Pager<BookClassify>();
		classifyPage.setPageNumber(pageNumber);
		classifyPage.setPageSize(pageSize);

		// 设置查询条件
		List<Criterion> cList = new ArrayList<Criterion>();
		if (classifyName != null && !classifyName.trim().isEmpty()) {
			cList.add(Restrictions.like("classifyName", classifyName, MatchMode.ANYWHERE));
		}
		return classifyDao.getPageRecords(BookClassify.class, cList, classifyPage);
	}

	@Override
	public void modifyClassify(BookClassify classify) throws Exception {
		// TODO Auto-generated method stub
		// 检查是否存在记录
		BookClassify old = classifyDao.getRecordById(BookClassify.class, classify.getClassifyId());
		if (old == null) {
			BusException exception = new BusException("分类已经被删除");
			exception.setReturnCode("A002");
			throw exception;
		}
		// 如分类名有修改，检查是否已存在同名的类型
		if (!old.getClassifyName().equals(classify.getClassifyName())) {
			List<Criterion> cList = new ArrayList<Criterion>();
			cList.add(Restrictions.eq("classifyName", classify.getClassifyName()));
			Long count = classifyDao.getRowCount(BookClassify.class, cList);
			if (count > 0) {
				BusException exception = new BusException("分类名重复");
				exception.setReturnCode("A003");
				throw exception;
			}
		}
		// 更新书籍分类
		old.setClassifyName(classify.getClassifyName());
		old.setDescription(classify.getDescription());
		old.setLastModifyDate(new Date());
		classifyDao.modifyRecord(old);
	}

	
	@Override
	public void deleteClassify(BookClassify classify) {
		// TODO Auto-generated method stub
		classifyDao.deleteRecordById(classify);
	}

}
