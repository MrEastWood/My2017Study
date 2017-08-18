package com.lxy.library.common;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author Administrator
 * 给easyui dataGrid返回的 json数据格式
 * @param <T> 查询的数据类型
 * 为了适配jquery easyui的dataGrid插件，数据总数 和 记录列表的属性名必须为total和 rows
 */
public class JsonDataGrid<T> {
	public int total;
	public List<T> rows = new ArrayList<T>();
	
	/* (non-Javadoc)
	 * 重写toString方法，格式化为json字符串
	 */
	public String toString(){
		//后面可以考虑在JavaConfig中，建立一个工厂方法产生Gson对象，通过spring注入
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this);
	}
}
