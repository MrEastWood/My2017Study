package com.corejava.mutithread.unlockbank;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
	
	private final double[] accounts;
	private Lock bankLock = new ReentrantLock();
	
	public Bank(int accNumber,double initAmount){
		
		accounts = new double[accNumber];
		for(int i = 0 ; i < accNumber ; i++){
			accounts[i] = initAmount;
		}
	}
	
	public void transfer(int from,int to,double amount){
		
		bankLock.lock();
		
		System.out.print(Thread.currentThread() + "--");
		if(accounts[from] >= amount){
			System.out.printf("%10.2f from %d to %d",amount,from,to);
			accounts[from] -= amount;
			accounts[to] += amount;
			System.out.printf("total balance : %10.2f",this.getTotal());
		}else{
			System.out.printf("bal not enough, bal : %10.2f , transfer amt : %10.2f",accounts[from],amount );
		}
		System.out.println();
		
		bankLock.unlock();
	}
	
	public double getTotal(){
		double total = 0;
		for(double a : accounts){
			total += a;
		}
		return total;
	}
	
	public int size(){
		
		return accounts.length;
	}
}
