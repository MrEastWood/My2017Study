package com.corejava.mutithread.deadlock;

public class DeadLockTest1 {

	/**
	 * t1先锁了account1 , 在未释放account1之前，尝试获得account2的锁
	 * t2先锁了account2 , 在未释放account2之前，尝试获得account1的锁
	 * 导致t1获得了account1的锁，获取不到account2的锁，t2获取了account2的锁，获取不到account1的锁，造成死锁
	 */
	public static void main(String[] args) {
		
		Account a1 = new Account("liuxingyi ", 1000);
		Account a2 = new Account("liangqitang", 1000);
		
		TransferRunnable transfer1 = new TransferRunnable(a1, a2, 10);
		TransferRunnable transfer2 = new TransferRunnable(a2, a1, 10);
		
		Thread t1 = new Thread(transfer1);
		Thread t2 = new Thread(transfer2);
		
		t1.start();
		t2.start();
		
	}
}
