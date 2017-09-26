package com.corejava.mutithread.unlockbank;

public class UnSyncBankTest {
	
	private static final int ACCOUNTNUM = 100;
	private static final double INITAMOUNT = 1000;
	
	public static void main(String[] args) {
		
		Bank bank =  new Bank(ACCOUNTNUM, INITAMOUNT);
		for(int i = 0; i < ACCOUNTNUM; i++){
			TransferThread r = new TransferThread(bank, i, INITAMOUNT);
			Thread t = new Thread(r);
			t.start();
		}
	}
}
