package com.lxy.library.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lxy.library.common.GsonExclusionStrategy;
import com.lxy.library.common.MsgUtil;
import com.lxy.library.common.ReturnMessage;
import com.lxy.library.entry.BookClassify;
import com.lxy.library.entry.BrrowNow;
import com.lxy.library.entry.Reader;
import com.lxy.library.service.BrrowService;

@Controller
@RequestMapping(value="/Brrow",produces = "application/json;charset=UTF-8")
public class BrrowController {
	
	@Autowired
	private BrrowService brrowService;
	
	/**
	 * 添加用户
	 * @param classify
	 * @return
	 */
	
	@RequestMapping(value="/queryInBrrow")
	@ResponseBody
	public String queryInBrrow(String readerId){
		System.out.println("***************" + readerId);
		List<BrrowNow> inBrrowList = brrowService.queryInBrrow(readerId);
		
		GsonExclusionStrategy strategy = new GsonExclusionStrategy(OneToMany.class);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").addSerializationExclusionStrategy(strategy).create();
		return gson.toJson(inBrrowList);
		
	}
	
	@RequestMapping(value="/brrowBook")
	@ResponseBody
	public String brrowBook(HttpServletRequest request){
		
		ReturnMessage msg = null;
		
		String readerId = request.getParameter("readerId");
		String bookId = request.getParameter("bookId");
		
		try {
			brrowService.brrowBook(readerId, bookId);
			msg = MsgUtil.genNormalMsg("借书成功");
		} catch (Exception e) {
			msg = MsgUtil.genErrMsg(e);
			e.printStackTrace();
		}
		
		return msg.toString();
		
	}
	
	@RequestMapping(value="/returnBook")
	@ResponseBody
	public String returnBook(HttpServletRequest request){
		
		ReturnMessage msg = null;
		
		String bookId = request.getParameter("bookId");
		try{
			brrowService.returnBook(bookId);
			msg = MsgUtil.genNormalMsg("还书成功");
		}catch(Exception e){
			msg = MsgUtil.genErrMsg(e);
		}
		
		return msg.toString();
		
	}
	
}