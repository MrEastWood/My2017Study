package com.test.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.test.common.MsgUtil;
import com.test.common.ReturnMessage;
import com.test.entry.Book;
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
	 */
	@RequestMapping(value="/addBook")
	public ModelAndView addClassify(@RequestParam("bookCover") MultipartFile file,HttpServletRequest request){
		// 判断文件是否为空  
        if (!file.isEmpty()) {  
            try {  
                // 文件保存路径  
                String filePath = request.getSession().getServletContext().getRealPath("/") + "upload/bookCover/"  
                        + file.getOriginalFilename();  
                // 转存文件  
                System.out.println(filePath);
                file.transferTo(new File(filePath));  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        } 
		ModelAndView modeView = new ModelAndView();
//		
//		ReturnMessage msg = null;
//		
//		try {
//			bookService.addBook(book);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block	
//			msg = MsgUtil.genErrMsg(e);
//			modeView.setViewName("../errorPage.jsp");
//		}
//		
//		if(msg == null){
//			msg = MsgUtil.genNormalMsg(book.getBookName());
//			modeView.setViewName("../successPage.jsp");
//		}
//		
//		modeView.addObject("message", msg);
//		
		return modeView;
	}

}
