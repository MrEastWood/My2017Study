package com.corejava.mutithread;

public class ThreadByImpl {
	
	public static void main(String[] args) {
		
		Thread3 t3 = new Thread3();
		Thread t1 = new Thread(t3);
		Thread t2 = new Thread(t3);
		
		t1.start();
		t2.start();
	}
	
}

class Thread3 implements Runnable{
	int i = 0;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(;i < 100;i++){
			System.out.println(this + " : " + i);
			
			try {
				Thread.sleep((long)(Math.random() * 500));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}