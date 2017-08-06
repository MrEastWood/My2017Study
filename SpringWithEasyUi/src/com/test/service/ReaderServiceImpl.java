package com.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.common.BusException;
import com.test.common.Pager;
import com.test.dao.ReaderDao;
import com.test.entry.Reader;

@Service("readerService")
@Transactional(rollbackFor = Exception.class)
public class ReaderServiceImpl implements ReaderService {
	@Autowired
	private ReaderDao readerDao;
	
	@Override
	public void addReader(Reader reader) throws Exception {
		
		//设置用户信息
		reader.setCreateDate(new Date());
		reader.setStatus("N");
		
		//检查id是否已存在
		Reader r = readerDao.getRecordById(Reader.class, reader.getReaderId());
		if(r != null){
			BusException e = new BusException("用户ID重复");
			e.setReturnCode("D001");
			throw e;
		}
		//检查用户名是否存在
		List<Criterion> crList = new ArrayList<Criterion>();
		crList.add(Restrictions.eq("name", reader.getName()));
		Long count = readerDao.getRowCount(Reader.class, crList);
		if(count > 0){
			BusException e = new BusException("用户名重复");
			e.setReturnCode("D002");
			throw e;
		}
		//插入记录
		readerDao.insertRecord(reader);
	}

	@Override
	public List<Reader> listReader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pager<Reader> queryReaderPage(int pageNumber, int pageSize, String ReaderName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modifyReader(Reader reader) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteReader(Reader reader) {
		// TODO Auto-generated method stub

	}

}
