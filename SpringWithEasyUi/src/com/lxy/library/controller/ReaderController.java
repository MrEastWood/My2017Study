package com.lxy.library.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lxy.library.common.BusException;
import com.lxy.library.common.MsgUtil;
import com.lxy.library.common.Pager;
import com.lxy.library.common.ReturnMessage;
import com.lxy.library.entry.Book;
import com.lxy.library.entry.BookClassify;
import com.lxy.library.entry.Reader;
import com.lxy.library.service.ClassifyService;
import com.lxy.library.service.ReaderService;

@Controller
@RequestMapping(value="/Reader",produces = "application/json;charset=UTF-8")
public class ReaderController {
	
	@Autowired
	private ReaderService readerService;
	
	/**
	 * 添加用户
	 * @param classify
	 * @return
	 */
	@RequestMapping(value="/addReader")
	@ResponseBody
	public String addReader(Reader reader){
		ReturnMessage msg = null;
		
		try {
			readerService.addReader(reader);
			msg = MsgUtil.genNormalMsg("添加用户成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block	
			msg = MsgUtil.genErrMsg(e);
		}
		return msg.toString();
	}
	
	@RequestMapping(value="/findById")
	@ResponseBody
	public String findByid(String readerId){
		ReturnMessage msg = null;
		try{
			Reader reader = readerService.findById(readerId);
			msg = MsgUtil.genNormalMsg(reader);
		}catch(Exception e){
			msg = MsgUtil.genErrMsg(e);
		}
		return msg.toString();
	}
	
	/**
	 * 分页查询用户
	 * @param request
	 * @return JSON格式的分页数据
	 */
	@RequestMapping(method=RequestMethod.POST,value="/queryReaderPage")
	@ResponseBody
	public String queryReaderPage(HttpServletRequest request){
		//获取查询条件
		Map<String,String> parmMap = new HashMap<String,String>();
		parmMap.put("readerId", request.getParameter("readerId"));
		parmMap.put("name", request.getParameter("name"));
		parmMap.put("permission", request.getParameter("permission"));
		//获取分页信息
		int pageNumber = Integer.valueOf(request.getParameter("page"));
		int pageSize = Integer.valueOf(request.getParameter("rows"));
		
		Pager<Reader> readerPage = readerService.queryReaderPage(pageNumber, pageSize, parmMap);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(readerPage);
		
	}
	
	@RequestMapping(value="/editReader")
	@ResponseBody
	public String editReader(Reader reader){
		ReturnMessage msg = null;
		try{
			readerService.modifyReader(reader);
			msg = MsgUtil.genNormalMsg("修改用户成功");
		}catch(Exception e){
			msg = MsgUtil.genErrMsg(e);
		}
		return msg.toString();
	}
}