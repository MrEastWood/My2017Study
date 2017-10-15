package com.corejava.collections;

import java.util.Stack;

import org.junit.Test;

public class StackTest {

	@Test
	public void test1(){
		
		Stack<String> stack = new Stack<String>();
		stack.push("AAAA");
		stack.push("BBBB");
		stack.push("CCCC");
		
		System.out.println(stack.peek());
		System.out.println(stack);
		
		System.out.println(stack.pop());
		System.out.println(stack);
		
	}
}
