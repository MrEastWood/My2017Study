package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.test.common.MsgUtil;
import com.test.common.ReturnMessage;
import com.test.entry.Book;
import com.test.service.BookService;

@Controller
@RequestMapping(value="/Book",produces = "application/json;charset=UTF-8")
public class BookContorller {
	
	@Autowired
	private BookService bookService;
	
	/**
	 * 添加分类
	 * @param classify
	 * @return
	 */
	@RequestMapping(value="/addBook")
	public ModelAndView addClassify(Book book){
		
		ModelAndView modeView = new ModelAndView();
		
		ReturnMessage msg = null;
		
		try {
			bookService.addBook(book);
		} catch (Exception e) {
			// TODO Auto-generated catch block	
			msg = MsgUtil.genErrMsg(e);
			modeView.setViewName("../errorPage.jsp");
		}
		
		if(msg == null){
			msg = MsgUtil.genNormalMsg(book.getBookName());
			modeView.setViewName("../successPage.jsp");
		}
		
		modeView.addObject("message", msg);
		
		return modeView;
	}

}
