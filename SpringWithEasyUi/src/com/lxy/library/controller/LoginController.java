package com.lxy.library.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lxy.library.common.JsonDataGrid;
import com.lxy.library.entry.BookClassify;
import com.lxy.library.entry.User;

@Controller
@RequestMapping("/Login")
public class LoginController {
	
	@RequestMapping("/login")
	public String Login(HttpServletRequest request){
		
		request.getSession().setAttribute("loginUser", request.getParameter("username"));
		
		return "redirect:/homePage.jsp";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		
		request.getSession().removeAttribute("loginUser");
		
		return "redirect:/login.jsp";
	}
}
