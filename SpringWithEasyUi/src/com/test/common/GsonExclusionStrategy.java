package com.test.common;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class GsonExclusionStrategy implements ExclusionStrategy {
	
	private List<String> skipFieldList;
	private List<Class> skipAnnotationList;
	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		//以字段名跳过
		if(skipFieldList != null){
			for(String s: skipFieldList){
				if(f.getName().equals(s)){
					return true;
				}
			}
		}
		//以注解跳过
		if(skipAnnotationList != null){
			for(Class c: skipAnnotationList){
				if(f.getAnnotation(c) != null){
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public GsonExclusionStrategy(String ...names ){
		skipFieldList = new ArrayList<String>();
		for(String s: names){
			skipFieldList.add(s);
		}
	}
	
	public GsonExclusionStrategy(Class ...classList ){
		skipAnnotationList = new ArrayList<Class>();
		for(Class c: classList){
			skipAnnotationList.add(c);
		}
	}
	
	public GsonExclusionStrategy setSkipFieldList(String ...names ){
		
		skipFieldList = new ArrayList<String>();
		for(String s: names){
			skipFieldList.add(s);
		}
		return this;
	}
	
	public GsonExclusionStrategy setSkipAnnotationList(Class ...classList ){
		skipAnnotationList = new ArrayList<Class>();
		for(Class c: classList){
			skipAnnotationList.add(c);
		}
		return this;
	}

}
