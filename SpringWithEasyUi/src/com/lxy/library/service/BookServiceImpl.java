package com.lxy.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lxy.library.common.BusException;
import com.lxy.library.common.Pager;
import com.lxy.library.dao.BookDao;
import com.lxy.library.dao.ClassifyDao;
import com.lxy.library.entry.Book;
import com.lxy.library.entry.BookClassify;

@Service("bookService")
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl implements BookService {

	@Autowired
	protected BookDao bookDao;
	@Autowired
	protected ClassifyDao classifyDao;

	@Override
	public void addBook(Book book) throws Exception {

		// 设置状态为正常
		book.setBookStatus("N");
		// 检查是否已存在重复的id
		Book old = bookDao.getRecordById(Book.class, book.getBookId());
		if (old != null) {
			BusException e = new BusException("书籍编号重复");
			e.setReturnCode("B001");
			throw e;
		}

		// 检查分类是否存在
		BookClassify cls = classifyDao.getRecordById(BookClassify.class, book
				.getBookClassify().getClassifyId());
		if (cls == null) {
			BusException e = new BusException("书籍分类不存在");
			e.setReturnCode("B002");
			throw e;
		}
		bookDao.insertRecord(book);
	}

	@Override
	public Pager<Book> queryBookPage(int pageNumber, int pageSize,
			Map<String, String> parms) {
		// 设置分页信息
		Pager<Book> bookPage = new Pager<Book>();
		bookPage.setPageNumber(pageNumber);
		bookPage.setPageSize(pageSize);

		// 设置查询条件
		List<Criterion> cList = new ArrayList<Criterion>();
		String bookClassify = parms.get("bookClassify");
		String bookName = parms.get("bookName");
		String bookAuthor = parms.get("bookAuthor");
		String bookPublish = parms.get("bookPublish");
		String priceFrom = parms.get("priceFrom");
		String priceTo = parms.get("priceTo");
		String bookSts = parms.get("bookSts");

		if (bookClassify != null && !bookClassify.trim().isEmpty()) {
			int classifyId = Integer.valueOf(bookClassify);
			BookClassify c = new BookClassify();
			c.setClassifyId(classifyId);
			cList.add(Restrictions.eq("bookClassify", c));
		}

		if (bookName != null && !bookName.trim().isEmpty()) {
			cList.add(Restrictions.like("bookName", bookName,
					MatchMode.ANYWHERE));
		}

		if (bookAuthor != null && !bookAuthor.trim().isEmpty()) {
			cList.add(Restrictions.like("bookAuthor", bookAuthor,
					MatchMode.ANYWHERE));
		}

		if (bookPublish != null && !bookPublish.trim().isEmpty()) {
			cList.add(Restrictions.like("bookPublish", bookPublish,
					MatchMode.ANYWHERE));
		}

		if (priceFrom != null && !priceFrom.trim().isEmpty()) {
			float price = Float.valueOf(priceFrom);
			cList.add(Restrictions.ge("bookPrice", price));
		}

		if (priceTo != null && !priceTo.trim().isEmpty()) {
			float price = Float.valueOf(priceTo);
			cList.add(Restrictions.le("bookPrice", price));
		}

		if (bookSts != null && !bookSts.trim().isEmpty()) {
			cList.add(Restrictions.eq("bookStatus", bookSts));
		}

		return bookDao.getPageRecords(Book.class, cList, bookPage);
	}

	@Override
	public Book loadBookForm(String id) {
		// TODO Auto-generated method stub
		Book book = bookDao.getRecordById(Book.class, id);
		return book;
	}

	@Override
	public void editBook(Book book) throws Exception {
		Book old = bookDao.getRecordByIdUpd(Book.class, book.getBookId());
		if (old == null) {
			BusException e = new BusException("需要编辑的记录不存在");
			e.setReturnCode("B002");
			throw e;
		}
		old.setBookName(book.getBookName());
		old.setBookClassify(book.getBookClassify());
		old.setBookDescription(book.getBookDescription());
		old.setBookPublish(book.getBookPublish());
		old.setBookAuthor(book.getBookAuthor());
		old.setBookPrice(book.getBookPrice());
		String url = book.getBookImageUrl();
		if (url != null && !url.trim().isEmpty()) {
			old.setBookImageUrl(url);
		}
		bookDao.modifyRecord(old);
	}

	@Override
	public void deleteBook(Book book) throws Exception {
		// TODO Auto-generated method stub
		Book old = bookDao.getRecordById(Book.class, book.getBookId());
		if (old == null) {
			BusException e = new BusException("需要删除的记录不存在");
			e.setReturnCode("B003");
			throw e;
		}
		bookDao.deleteRecordById(old);
	}
}
