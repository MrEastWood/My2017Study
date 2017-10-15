package com.corejava.collections;

import java.util.Comparator;
import java.util.TreeSet;

import org.junit.Test;

public class TreeSetTest {

	/**
	 * TreeSet对已经实现Comparable接口的元素进行排序
	 */
	@Test
	public void test1(){
		
		TreeSet<String> strSet = new TreeSet<String>();
		strSet.add("AAA");
		strSet.add("CCC");
		strSet.add("BBB");
		strSet.add("ABCDE");
		
		System.out.println(strSet);
		
	}
	
	/**
	 * 如treeSet创建时未传入Comparator接口，添加的元素又未实现Comparable接口，会报异常
	 */
	@Test
	public void test2(){
		TreeSet<Student> stuSet = new TreeSet<Student>();
		
		stuSet.add(new Student(20));
		stuSet.add(new Student(60));
		stuSet.add(new Student(50));
		stuSet.add(new Student(30));
		stuSet.add(new Student(40));
		
		System.out.println(stuSet);
	}
	
	/**
	 * 在TreeSet创建时传入Comparator接口，可以按照接口定义的顺序进行排序，且元素无需实现Comparable接口
	 */
	@Test
	public void test3(){
		
		TreeSet<Student> stuSet = new TreeSet<Student>(new StuComparator());
		stuSet.add(new Student(20));
		stuSet.add(new Student(60));
		stuSet.add(new Student(50));
		stuSet.add(new Student(30));
		stuSet.add(new Student(40));
		
		System.out.println(stuSet);
		
	}
	
	class StuComparator implements Comparator<Student>{

		@Override
		public int compare(Student o1, Student o2) {
			
			return o1.getScore() - o2.getScore();
		}
	}
}

class Student {
	
	private int score;
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Student(int score){
		this.score = score;
	}
	
	public String toString(){
		return String.valueOf(score);
	}
	
}