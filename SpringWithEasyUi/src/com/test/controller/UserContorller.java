package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.entry.JsonDataGrid;
import com.test.entry.User;

@Controller
public class UserContorller {
	
	@RequestMapping("/getUserList")
	@ResponseBody
	public String getUserList(){
		JsonDataGrid<User> jsDataGrid = new JsonDataGrid<User>();
		
		for(int i = 0;i < 10 ; i++){
			User user = new User();
			user.setId("S00000" + i);
			user.setName("user" + i);
			user.setAge(10 + i);
			user.setRemark("remark " + i);
			jsDataGrid.total++;
			jsDataGrid.rows.add(user);
		}
		
		Gson gson = new GsonBuilder().create();
		String str = gson.toJson(jsDataGrid);
		System.out.println(str);
		return str;
		
		
		
		
	}
}
