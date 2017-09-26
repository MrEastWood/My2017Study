package com.corejava.mutithread.deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	
	private String accountName;
	private double balance;
	Lock lock = new ReentrantLock();
	
	public Account(String name,double bal){
		this.accountName = name;
		this.balance = bal;
	}
	
	public int debit(double amt){

		if(balance >= amt){
			balance -= amt;
			System.out.printf("%s debit %5.2f \n",accountName,amt );
			return 0;
		}else{
			System.out.printf("bal not enough %5.2f --> %5.2f \n",balance,amt);
			return -1;
		}
	}
	
	public void credit(double amt){
		
		balance += amt;
		System.out.printf("%s credit %5.2f \n",accountName,amt );
		
	}
	
	public void lock(){
		lock.lock();
	}
	
	public void unlock(){
		lock.unlock();
	}
}
