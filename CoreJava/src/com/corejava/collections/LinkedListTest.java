package com.corejava.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void test1(){
		
		LinkedList<String> strLinkedList = new LinkedList<String>();
		strLinkedList.add("AAA");
		strLinkedList.add("BBB");
		strLinkedList.add("CCC");
		System.out.println(strLinkedList);
		
	}
	
	
	/**
	 * 测试在插入数据时，LinkedList和ArrayList的性能差异
	 * 测试结果：LinkedList插入数据性能有明显的优势，且在数据量越大的的时候，优势越明显
	 */
	@Test
	public void test2(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("输入要测试的条数");
		int count = sc.nextInt();
		List<Integer> listL = new LinkedList<Integer>();
		List<Integer> listA = new ArrayList<Integer>();
		//测试LinkedList的插入性能
		System.out.println("LinkedList插入" + count + "条数据");
		long timeCost1 = testAdd(count,listL);
		System.out.println("ArrayList插入" + count + "条数据");
		long timeCost2 = testAdd(count,listA);
		System.out.println("性能差距 ：" + timeCost2 / timeCost1 + "倍");

		sc.close();
	}
	
	/**
	 * 测试在随机读取数据时，LinkedList和ArrayList的性能差异
	 * 测试结果：ArrayList随机读取性能有明显的优势，且在数据量越大的的时候，优势越明显
	 */
	@Test
	public void test3(){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("输入要测试的条数");
		int count = sc.nextInt();
		List<Integer> listL = new LinkedList<Integer>();
		List<Integer> listA = new ArrayList<Integer>();
		for(int i = 0;i < count;i++){
			listL.add(i);
			listA.add(i);
		}
		//测试LinkedList的插入性能
		System.out.println("LinkedList读取" + count + "条数据");
		long timeCost1 = testGet(listL);
		System.out.println("ArrayList读取" + count + "条数据");
		long timeCost2 = testGet(listA);
		System.out.println("性能差距 ：" + timeCost1 / timeCost2 + "倍");

		sc.close();
	}
	
	public long testAdd(int count,List<Integer> list){
		
		long timeBef = System.currentTimeMillis();
		System.out.println("开始时间 ： " + timeBef);
		for(int i=0 ; i < count ; i++){
			list.add(0,i);
		}
		long timeAft = System.currentTimeMillis();
		System.out.println("结束时间 ：" + timeAft);
		System.out.println("耗费时间 ：" + (timeAft - timeBef));
		return timeAft - timeBef;
	}
	
	public long testGet(List<Integer> list){
		
		int count = list.size();
		
		long timeBef = System.currentTimeMillis();
		System.out.println("开始时间 ： " + timeBef);
		for(int i=0 ; i < count ; i++){
			list.get(i);
		}
		long timeAft = System.currentTimeMillis();
		System.out.println("结束时间 ：" + timeAft);
		System.out.println("耗费时间 ：" + (timeAft - timeBef));
		return timeAft - timeBef;
	}
}
