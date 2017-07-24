package com.test.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.common.BusException;
import com.test.common.MsgUtil;
import com.test.common.Pager;
import com.test.common.ReturnMessage;
import com.test.entry.BookClassify;
import com.test.service.ClassifyService;

@Controller
@RequestMapping(produces = "application/json;charset=UTF-8")
public class ClassifyContorller {
	
	@Autowired
	private ClassifyService classifyService;
	
	@RequestMapping(value="/addClassify")
	@ResponseBody
	public String addClassify(BookClassify classify){
		System.out.println("--------------开始插入一个分类--------------------");
		classify.setCreateDate(new Date());
		classify.setLastModifyDate(new Date());
		classify.setClassifyID(1);
		ReturnMessage msg = null;
		
		try {
			classifyService.addClassify(classify);
		} catch (Exception e) {
			// TODO Auto-generated catch block	
			msg = MsgUtil.genErrMsg(e);
		}
		
		if(msg == null){
			msg = MsgUtil.genNormalMsg(classify.getDescription());
		}
		
		return msg.toString();
	}
	
	@RequestMapping(value="/listClassify")
	@ResponseBody
	public String listClassify(){
		List<BookClassify> classifyList = classifyService.listClassify();
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(classifyList);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/queryClassifyPage")
	@ResponseBody
	public String queryClassifyPage(HttpServletRequest request){
		//获取查询条件
		String classifyName = request.getParameter("classifyName");
		//获取分页信息
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		
		Pager<BookClassify> classifyPage = classifyService.queryClassifyPage(pageNumber, pageSize, classifyName);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(classifyPage);
		
	}
	
}
