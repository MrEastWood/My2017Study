package com.corejava.mutithread.unlockbank;

public class TransferThread implements Runnable {
	
	private Bank bank;
	private int from;
	private double max;
	private int DELAY = 10;
	
	public TransferThread(Bank b,int from,double max){
		
		this.bank = b;
		this.from = from;
		this.max = max;
	}

	@Override
	public void run() {

		while(true){
			int to = (int)(Math.random() *  bank.size());
			double amount = Math.random() * max;
			bank.transfer(from, to, amount);
			try {
				Thread.sleep((int) (Math.random() * DELAY));
			} catch (InterruptedException e) {
				
			}
		}
		
	}

}
