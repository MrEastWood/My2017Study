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
import com.test.dao.BrrowNowDao;
import com.test.dao.ReaderDao;
import com.test.entry.Book;
import com.test.entry.BrrowNow;
import com.test.entry.Reader;

@Service("brrowService")
@Transactional(rollbackFor = Exception.class)
public class BrrowServiceImpl implements BrrowService {
	
	@Autowired
	protected BrrowNowDao brrowNowDao;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateStr = sdf.format(date);
		String journal = commonService.getJournal(dateStr);
		
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

}
