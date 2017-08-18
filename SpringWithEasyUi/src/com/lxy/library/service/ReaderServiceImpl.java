package com.lxy.library.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxy.library.common.BusException;
import com.lxy.library.common.Pager;
import com.lxy.library.dao.ReaderDao;
import com.lxy.library.entry.Book;
import com.lxy.library.entry.Reader;

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
	public Pager<Reader> queryReaderPage(int pageNumber, int pageSize, Map<String, String> parms) {
		
		// 设置分页信息
		Pager<Reader> readerPage = new Pager<Reader>();
		readerPage.setPageNumber(pageNumber);
		readerPage.setPageSize(pageSize);
		
		// 设置查询条件
		List<Criterion> cList = new ArrayList<Criterion>();
		String readerId = parms.get("readerId");
		String name = parms.get("name");
		String permission = parms.get("permission");
		
		if (readerId != null && !readerId.trim().isEmpty()) {
			cList.add(Restrictions.eq("readerId", readerId));
		}
		
		if (name != null && !name.trim().isEmpty()) {
			cList.add(Restrictions.like("name", name,
					MatchMode.ANYWHERE));
		}
		
		if (permission != null && !permission.trim().isEmpty()) {
			cList.add(Restrictions.eq("permission", permission));
		}
		
		return readerDao.getPageRecords(Reader.class, cList, readerPage);
	}

	@Override
	public void modifyReader(Reader reader) throws Exception {
		// TODO Auto-generated method stub
		Reader old = readerDao.getRecordById(Reader.class,reader.getReaderId());
		
		if(old == null){
			BusException e = new BusException("需要编辑的用户不存在");
			e.setReturnCode("D005");
			throw e;
		}
		
		old.setPermission(reader.getPermission());
		old.setRemark(reader.getRemark());
		old.setStatus(reader.getStatus());
		
		readerDao.modifyRecord(old);
	}

	@Override
	public void deleteReader(String readerId) throws Exception  {
		// TODO Auto-generated method stub
		Reader reader = readerDao.getRecordById(Reader.class,readerId);
		
		if(reader == null){
			BusException e = new BusException("需要删除的用户不存在");
			e.setReturnCode("D005");
			throw e;
		}
		
		readerDao.deleteRecordById(reader);
	}

	@Override
	public Reader findById(String readerId) throws Exception {
		// TODO Auto-generated method stub
		
		Reader reader = readerDao.getRecordById(Reader.class,readerId);
		if(reader == null){
			BusException e = new BusException("用户不存在");
			e.setReturnCode("D004");
			throw e;
		}
		return reader;
	}

}
