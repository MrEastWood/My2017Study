package com.corejava.mutithread.deadlock;

public class DeadLockTest1 {

	/**
	 * t1������account1 , ��δ�ͷ�account1֮ǰ�����Ի��account2����
	 * t2������account2 , ��δ�ͷ�account2֮ǰ�����Ի��account1����
	 * ����t1�����account1��������ȡ����account2������t2��ȡ��account2��������ȡ����account1�������������
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
