package com.corejava.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class ArrayListTest {

	@Test
	public void test1(){
		
		ArrayList<String> strList = new ArrayList<String>();
		
		//测试添加元素
		strList.add("AAA");
		strList.add("BBB");
		
		//测试添加集合，TreeSet会自动排序
		Set<String> strSet = new TreeSet<String>();
		strSet.add("DDD");
		strSet.add("CCC");
		strSet.add("EEE");
		
		strList.addAll(strSet);
		
		System.out.println(strList);
		
		//测试迭代
		for(String s : strList){
			System.out.println(s);
		}
		
		//使用Iterator
		Iterator<String> iterator = strList.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
		
	}
}

