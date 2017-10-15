package com.corejava.collections;

import java.util.Iterator;
import java.util.Vector;

import org.junit.Test;

public class VectorTest {
	
	/**
	 * Vector是线程同步的
	 * 在集合对象会被多个线程操作的时候，使用Vector已保持线程安全
	 */
	@Test
	public void testAdd(){
		
		Vector<String> vector = new Vector<String>();
		vector.add("AAA");
		vector.add("BBB");
		vector.add("CCC");
		vector.add("AAA");
		
		//第一种迭代方法，使用for语句
		System.out.println("使用for语句遍历vector的元素");
		for(String s : vector){
			System.out.println(s);
		}
		
		//第二种迭代方法，使用迭代器
		System.out.println("使用迭代器遍历vector的元素");
		Iterator<String> vectorIterator = vector.iterator();
		while(vectorIterator.hasNext()){
			System.out.println(vectorIterator.next());
		}
		
		//第三种迭代方法，获取vector的size，逐一获取
		System.out.println("获取vector的size，逐一获取vector元素");
		int size = vector.size();
		for(int i=0;i < size ; i++){
			System.out.println(vector.get(i));
		}
		
		
		//remove方法使用equals判断object是否已存在，所以对于string来讲，不需要是同一个object才删除
		vector.remove("AAA");
		System.out.println(vector);
	}
}
