package com.test.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.entry.BookClassify;
import com.test.entry.ReturnMessage;

@Controller
@RequestMapping(produces = "application/json;charset=UTF-8")
public class ClassifyContorller {

	@RequestMapping(value="/addClassify")
	@ResponseBody
	public String addClassify(BookClassify classify) throws UnsupportedEncodingException{
		//System.out.println(new String("中文".getBytes("UTF-8"),"UTF-8"));
		System.out.println("name : " + classify.getClassifyName());
		System.out.println("desc : " + classify.getDescription());
		ReturnMessage msg = new ReturnMessage();
		msg.setMessageType("N");
		msg.setReturnCode("0000");
		
		//测试中文的编码类型
		String s = classify.getDescription();
		
		if(s.equals(new String(s.getBytes("UTF-8"),"UTF-8"))){
			System.out.println("字符编码是utf-8");
		}else{
			System.out.println("字符编码不是utf-8");
		}
		
		msg.setMessageData(classify.getDescription());
		return msg.toString();
	}
}
