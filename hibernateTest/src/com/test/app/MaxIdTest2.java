package com.test.app;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;

import com.test.firstclass.MaxId;
import com.test.util.HibernateUtil;

public class MaxIdTest2 {
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 50; i++) {
			GetId t = new GetId();
			t.setIdType("journal");
			t.setIdPrefix("20170716");
			t.start();
		}
	}
}

class GetId extends Thread {
	
	private String idType;
	private String idPrefix;
	
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			try {
				sleep((int) (Math.random() * 100));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.beginTransaction();

			int curValue = 1;
			MaxId maxid = new MaxId();
			maxid.setIdType(idType);
			maxid.setIdPrefix(idPrefix);

			MaxId oldId = (MaxId) session.get(MaxId.class, maxid.getIdType(),
					LockOptions.UPGRADE);

			if (oldId == null) {
				System.out.println("错误，id记录不存在，请检查");
				
			} else {
				//检查前缀是否存在
				if(oldId.getIdPrefix().equals(maxid.getIdPrefix())){
					curValue = oldId.getCurValue() + 1;

				}else{
					oldId.setIdPrefix(maxid.getIdPrefix());
				}
				oldId.setCurValue(curValue);
				session.update(oldId);
			}
			
			session.getTransaction().commit();
			System.out.println(this.getName() + " generate id : " + oldId);
		}
	}
	
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	
	public String getIdPrefix() {
		return idPrefix;
	}
	public void setIdPrefix(String idPrefix) {
		this.idPrefix = idPrefix;
	}
}
