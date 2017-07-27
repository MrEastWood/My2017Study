package com.test.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.GsonBuilder;
import com.test.common.HttpUtil;
import com.test.common.MsgUtil;
import com.test.common.ReturnMessage;
import com.test.entry.Book;
import com.test.entry.BookClassify;
import com.test.service.BookService;

@Controller
@RequestMapping(value="/Book",produces = "application/json;charset=UTF-8")
public class BookContorller {
	//@Autowired
	//public HttpServletRequest request;
	@Autowired
	private BookService bookService;
	
	/**
	 * 添加分类
	 * @param classify
	 * @return
	 * @throws  
	 */
	@RequestMapping(value="/addBook")
	public ModelAndView addClassify(@RequestParam("bookCover") MultipartFile file,HttpServletRequest request){
		
		Book b;
		ModelAndView modeView = new ModelAndView();
		ReturnMessage msg = null;
		System.out.println("aaaa");
		//获取book参数
		try{
			b  = (Book)HttpUtil.getRequestObject(Book.class, request);
			BookClassify c = new BookClassify();
			int classifyId = Integer.valueOf(request.getParameter("bookClassify"));
			c.setClassifyId(classifyId);
			b.setBookClassify(c);
		}catch(Exception e){
			msg = MsgUtil.genErrMsg(e);
			modeView.setViewName("../errorPage.jsp");
			modeView.addObject("message",msg);
			return modeView;
		}

		//保存封面图片操作
        if (!file.isEmpty()) {  
            try {  
                // 文件保存路径  
            	String path = "upload/bookCover/" + b.getBookId() + file.getOriginalFilename();
                String filePath = request.getSession().getServletContext().getRealPath("/") + path;
                // 转存文件  
                System.out.println(filePath);
                file.transferTo(new File(filePath));  
                
                b.setBookImageUrl(path);
            } catch (Exception e) {  
            	modeView.setViewName("../errorPage.jsp");
            	msg = MsgUtil.genErrMsg(e);
            	modeView.addObject("message",msg);
    			return modeView;
            }  
        } 

        //插入新增书籍
        try {
			bookService.addBook(b);
		} catch (Exception e) {
			modeView.setViewName("../errorPage.jsp");
			msg = MsgUtil.genErrMsg(e);
			modeView.addObject("message",msg);
			return modeView;
		}
        
		modeView.setViewName("../successPage.jsp");
		msg = MsgUtil.genNormalMsg(b.getBookName());
		System.out.println(msg);
		modeView.addObject("message",msg);
		return modeView;
	}

}
