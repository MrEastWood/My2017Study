package com.test.junit;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.GsonBuilder;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hello");
		Map<String,String> m = new HashMap<String,String>();
		m.put("name", "aaa");
		m.put("value", "bbb");
		System.out.println(new GsonBuilder().create().toJson(m));
	}

}
