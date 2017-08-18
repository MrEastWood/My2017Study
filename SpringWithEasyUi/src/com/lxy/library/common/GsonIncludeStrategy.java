package com.lxy.library.common;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GsonIncludeStrategy implements ExclusionStrategy {
	
	private List<String> includeFieldList;
	private List<Class> includeAnnotationList;
	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		//如字段名存在字段列表中，不跳过
		if(includeFieldList != null){
			for(String s: includeFieldList){
				if(f.getName().equals(s)){
					return false;
				}
			}
		}
		//如注解名存在注解列表中，不跳过
		if(includeAnnotationList != null){
			for(Class c: includeAnnotationList){
				if(f.getAnnotation(c) != null){
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public GsonIncludeStrategy(String ...names ){
		includeFieldList = new ArrayList<String>();
		for(String s: names){
			includeFieldList.add(s);
		}
	}
	
	public GsonIncludeStrategy(Class ...classList ){
		includeAnnotationList = new ArrayList<Class>();
		for(Class c: classList){
			includeAnnotationList.add(c);
		}
	}
	
	public GsonIncludeStrategy setIncludeFieldList(String ...names ){
		
		includeFieldList = new ArrayList<String>();
		for(String s: names){
			includeFieldList.add(s);
		}
		return this;
	}
	
	public GsonIncludeStrategy setIncludeAnnotationList(Class ...classList ){
		includeAnnotationList = new ArrayList<Class>();
		for(Class c: classList){
			includeAnnotationList.add(c);
		}
		return this;
	}

}
