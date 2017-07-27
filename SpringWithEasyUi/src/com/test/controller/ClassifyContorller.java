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
import com.test.service.ClassifyService;

@Controller
@RequestMapping(value="/Classify",produces = "application/json;charset=UTF-8")
public class ClassifyContorller {
	
	@Autowired
	private ClassifyService classifyService;
	
	/**
	 * 添加分类
	 * @param classify
	 * @return
	 */
	@RequestMapping(value="/addClassify")
	public ModelAndView addClassify(BookClassify classify){
		
		ModelAndView modeView = new ModelAndView();
		
		ReturnMessage msg = null;
		
		try {
			classifyService.addClassify(classify);
		} catch (Exception e) {
			// TODO Auto-generated catch block	
			msg = MsgUtil.genErrMsg(e);
			modeView.setViewName("../errorPage.jsp");
			modeView.addObject("message", msg);
			return modeView;
		}
		
		msg = MsgUtil.genNormalMsg("添加分类成功!分类名 " + classify.getDescription());
		modeView.setViewName("../successPage.jsp");
		modeView.addObject("message", msg);
		return modeView;
	}
	
	/**
	 * 查询所有的分类
	 * @return
	 */
	@RequestMapping(value="/listClassify")
	@ResponseBody
	public String listClassify(){
		List<BookClassify> classifyList = classifyService.listClassify();
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(classifyList);
	}
	
	/**
	 * 分页查询分类
	 * @param request
	 * @return JSON格式的分页数据
	 */
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
	
	/**
	 * 修改分类
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,value="/modifyClassify")
	@ResponseBody
	public String modifyClassify(BookClassify classify){
		
		ReturnMessage msg = MsgUtil.genNormalMsg("修改分类成功");
		try {
			classifyService.modifyClassify(classify);
		} catch (Exception e) {
			msg = MsgUtil.genErrMsg(e);
		}
		
		return msg.toString();
	}
	
	/**
	 * 删除分类
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/deleteClassify")
	@ResponseBody
	public String deleteClassify(BookClassify classify){
		
		ReturnMessage msg = MsgUtil.genNormalMsg("删除分类成功");
		try {
			classifyService.deleteClassify(classify);
		} catch (Exception e) {
			e.printStackTrace();
			msg = MsgUtil.genErrMsg(e);
		}
		
		return msg.toString();
	}
}
