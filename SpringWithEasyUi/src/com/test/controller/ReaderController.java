package com.test.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.common.BusException;
import com.test.common.MsgUtil;
import com.test.common.Pager;
import com.test.common.ReturnMessage;
import com.test.entry.BookClassify;
import com.test.entry.Reader;
import com.test.service.ClassifyService;
import com.test.service.ReaderService;

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
	
}
