package com.corejava.proxy;

public class TargetImpl implements Target {
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void sayHi() {
		System.out.println("Hi!");

	}

	@Override
	public void sayBy() {
		System.out.println("By!");

	}

	@Override
	public void sayName() {
		System.out.println("Hi," + name);
		
	}
	
}
