package com.corejava.collections;

import java.util.HashSet;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Test;

/**
 * @author Administrator
 * HashSet的使用 - 集合会忽略重复的元素，先判断hashcode是否相同，如相同再判断是否equals，equals则会判断为是重复的元素
 * 所以对象重写equals方法，也需要重写hashcode方法，这样才会认为是相同的元素
 */
public class HashSetTest {

	/**
	 * 测试表明，元素是随机存储的(也不能认为是随机，存储同样的元素，顺序都是相同的，只是不按常规的方法排序)
	 */
	@Test
	public void test1(){
		
		HashSet<String> stringSet = new HashSet<String>();
		
		stringSet.add("BBB");
		stringSet.add("CCC");
		stringSet.add("AAA");
		stringSet.add("AAA");
		
		System.out.println(stringSet);
		
	}
	
	/**
	 * 1. 在不重写hashcode方法的时候，及时equals为true，HashSet也会认为两个元素不重复，set会包含两个元素
	 * 2. 在重写hashcode方法后，认为两个元素是相同的，set包含一个元素
	 */
	@Test
	public void test2(){
		
		Student s1 = new Student("张三", 20, 90);
		Student s2 = new Student("张三", 20, 90);
		
		System.out.println(s1.equals(s2));
		
		HashSet<Student> stuSet = new HashSet<Student>();
		stuSet.add(s1);
		stuSet.add(s2);
		
		System.out.println(stuSet);
		
		
	}
	
	class Student{
		
		private String name;
		private int age;
		private int score;
		
		public Student(String name,int age,int score){
			this.name = name;
			this.age = age;
			this.score = score;
		}
		
		@Override
		public boolean equals(Object o){
			
			if(o instanceof Student){
				Student s = (Student)o;
				if(s.getName().equals(this.name) && s.getAge() == this.age && s.getScore() == this.score ){
					return true;
				}
			}
			return false;
		}
		
		@Override
		public int hashCode(){
			
			Integer a = new Integer(age);
			Integer s = new Integer(score);
			return name.hashCode() + a.hashCode() + s.hashCode();
		}
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public int getScore() {
			return score;
		}

		public void setScore(int score) {
			this.score = score;
		}

		@Override
		public String toString(){
			
			String str = "name : " + this.name + ",age : " + this.age + ",score : " + this.score;
			return str;
			
		}
	}
}
