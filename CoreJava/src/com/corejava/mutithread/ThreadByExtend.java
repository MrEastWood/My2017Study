package com.corejava.mutithread;

public class ThreadByExtend extends Thread {
	
	public static void main(String[] args) throws InterruptedException {
		
		//由于是两个不同的对象，非静态变量值不受影响
		Thread1 t1 = new Thread1();
		Thread1 t2 = new Thread1();
		
		System.out.println("非静态变量");
		t1.start();
		t2.start();
		
		sleep(1000L);
		//静态变量，不同对象指向同一个静态 变量，会受影响
		Thread2 t3 = new Thread2();
		Thread2 t4 = new Thread2();
		
		System.out.println("静态变量");
		t3.start();
		t4.start();
		
		//测试中断线程，测试结果--处于休眠状态，不能被中断，抛出异常
		sleep(1000L);
		System.out.println("中断线程");
		t3.interrupt();
		t4.interrupt();
		
	}
}


class Thread1 extends Thread{
	int i = 0;
	@Override
	public void run() {
		for(;i < 100;i++){
			System.out.println(this + " : " + i);
		}	
	}
	
}

class Thread2 extends Thread{
	static int i = 0;
	@Override
	public void run() {
		for(;i < 100;i++){
			System.out.println(this + " : " + i);
			try {
				long l = (long)(Math.random() * 500);
				sleep(l);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
	}
	
}