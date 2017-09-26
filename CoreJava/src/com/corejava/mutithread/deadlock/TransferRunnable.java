package com.corejava.mutithread.deadlock;

public class TransferRunnable implements Runnable {
	
	private Account from;
	private Account to;
	private double amt;
	
	public TransferRunnable(Account from,Account to,double amt) {
		
		this.from = from;
		this.to = to;
		this.amt = amt;
	}
	
	@Override
	public void run() {
		
		from.lock();
		int returnCode = from.debit(amt);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(returnCode == 0){
			to.lock();
			to.credit(amt);
		}
		
		from.unlock();
		to.unlock();
		
	}
}
