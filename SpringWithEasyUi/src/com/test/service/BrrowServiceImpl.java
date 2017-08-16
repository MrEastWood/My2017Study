package com.test.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.common.BusException;
import com.test.dao.BookDao;
import com.test.dao.BrrowHisDao;
import com.test.dao.BrrowNowDao;
import com.test.dao.ReaderDao;
import com.test.entry.Book;
import com.test.entry.BrrowHis;
import com.test.entry.BrrowNow;
import com.test.entry.Reader;

@Service("brrowService")
@Transactional(rollbackFor = Exception.class)
public class BrrowServiceImpl implements BrrowService {
	
	@Autowired
	protected BrrowNowDao brrowNowDao;
	@Autowired
	protected BrrowHisDao brrowHisDao;
	@Autowired
	protected ReaderDao readerDao;
	@Autowired
	protected BookDao bookDao;
	@Autowired
	protected CommonService commonService;
	
	@Override
	public void brrowBook(String readerId,String bookId) throws Exception {
		// TODO Auto-generated method stub
		Reader reader = readerDao.getRecordById(Reader.class, readerId);
		if(reader == null){
			BusException e = new BusException("用户不存在");
			e.setReturnCode("E001");
			throw e;
		}
		
		if(!reader.getStatus().equals("N")){
			BusException e = new BusException("用户当前状态不可借书");
			e.setReturnCode("E005");
			throw e;
		}
		
		Book book = bookDao.getRecordById(Book.class,bookId);
		if(book == null){
			BusException e = new BusException("书籍不存在");
			e.setReturnCode("E002");
			throw e;
		}
		
		if(!book.getBookStatus().equals("N")){
			BusException e = new BusException("书籍非可借状态");
			e.setReturnCode("E003");
			throw e;
		}
		
		List<Criterion> cList = new ArrayList<Criterion>();
		cList.add(Restrictions.eq("reader.readerId", readerId));
		
		Long count = brrowNowDao.getRowCount(BrrowNow.class, cList);
		
		int maxnumber = Integer.valueOf(reader.getPermission()) * 5 ;
		if(count >= maxnumber){
			BusException e = new BusException("超出最大可借数量:" + maxnumber);
			e.setReturnCode("E004");
			throw e;
		}
		
		Date date = new Date();
		String journal = commonService.getJournal(date);
		
		BrrowNow brrowRec = new BrrowNow();
		brrowRec.setJournal(journal);
		brrowRec.setBook(book);
		brrowRec.setReader(reader);
		brrowRec.setBrrowDate(date);
		brrowRec.setTxnDate(date);
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, +1);
		Date expDate = calendar.getTime();
		brrowRec.setExpDate(expDate);
		
		book.setBookStatus("B");
		bookDao.modifyRecord(book);
		
		brrowNowDao.insertRecord(brrowRec);
		
	}

	@Override
	public List<BrrowNow> queryInBrrow(String readerId) {
		// TODO Auto-generated method stub
		List<Criterion> cList = new ArrayList<Criterion>();
		cList.add(Restrictions.eq("reader.readerId", readerId));
		
		List<BrrowNow> inBrrowList = brrowNowDao.getRecordList(BrrowNow.class, cList);
		
		return inBrrowList;
		
	}

	@Override
	public void returnBook(String bookId) throws Exception {
		
		//检查书籍是否存在
		Book book = bookDao.getRecordById(Book.class,bookId);
		if(book == null){
			BusException e = new BusException("书籍不存在");
			e.setReturnCode("E006");
			throw e;
		}
		
		//检查书籍是否处于借出状态
		if(!book.getBookStatus().equals("B")){
			BusException e = new BusException("书籍非借出状态");
			e.setReturnCode("E007");
			throw e;
		}
		
		//检查借出记录是否存在
		List<Criterion> cList = new ArrayList<Criterion>();
		cList.add(Restrictions.eq("book.bookId", bookId));
		List<BrrowNow> recList = brrowNowDao.getRecordList(BrrowNow.class, cList);
		if(recList.size() != 1){
			BusException e = new BusException("借出记录异常，共有" + recList.size() + "条借出记录");
			e.setReturnCode("E008");
			throw e;
		}
		
		//插入借还历史
		BrrowNow brrowNow = recList.get(0);
		Date date = new Date();
		String journal = commonService.getJournal(date);
		
		BrrowHis brrowHis = new BrrowHis();
		
		brrowHis.setJournal(journal);
		brrowHis.setReader(brrowNow.getReader());
		brrowHis.setBook(brrowNow.getBook());
		brrowHis.setBrrowTxnTime(brrowNow.getTxnDate());
		brrowHis.setReturnTxnTime(date);
		
		brrowHisDao.insertRecord(brrowHis);
		
		//删除在借记录
		brrowNowDao.deleteRecordById(brrowNow);
		
		//更新书籍状态
		book.setBookStatus("N");
		bookDao.modifyRecord(book);
	}
	
}
