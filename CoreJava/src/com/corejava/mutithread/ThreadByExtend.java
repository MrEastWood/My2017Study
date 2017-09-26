package com.corejava.mutithread;

public class ThreadByExtend extends Thread {
	
	public static void main(String[] args) throws InterruptedException {
		
		//������������ͬ�Ķ��󣬷Ǿ�̬����ֵ����Ӱ��
		Thread1 t1 = new Thread1();
		Thread1 t2 = new Thread1();
		
		System.out.println("�Ǿ�̬����");
		t1.start();
		t2.start();
		
		sleep(1000L);
		//��̬��������ͬ����ָ��ͬһ����̬ ����������Ӱ��
		Thread2 t3 = new Thread2();
		Thread2 t4 = new Thread2();
		
		System.out.println("��̬����");
		t3.start();
		t4.start();
		
		//�����ж��̣߳����Խ��--��������״̬�����ܱ��жϣ��׳��쳣
		sleep(1000L);
		System.out.println("�ж��߳�");
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