package com.corejava.mutithread;

public class ThreadByInnerClass {
	
	public static void main(String[] args) {
		Thread t1 = new Thread(){
			public void run() {
				for(int i = 0;i < 100;i++){
					System.out.println(this + " : " + i);
				}
			};
		};
		t1.start();
	}
}
