package com.corejava.collections;

import java.util.LinkedHashSet;

import org.junit.Test;

public class LinkedHashSetTest {

	/**
	 * 测试表明，LinkedHashSet是有序的。它按照元素添加的顺序存储元素
	 */
	@Test
	public void test1(){
		
		LinkedHashSet<String> set = new LinkedHashSet<String>();
		set.add("BBB");
		set.add("CCC");
		set.add("AAA");
		
		System.out.println(set);
	}
}
