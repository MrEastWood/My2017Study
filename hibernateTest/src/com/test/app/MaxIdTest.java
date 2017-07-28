package com.test.app;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;

import com.test.firstclass.MaxId;
import com.test.util.HibernateUtil;

public class MaxIdTest {
	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 50; i++) {
			GetIdThread t = new GetIdThread();
			t.start();
		}
	}
}

class GetIdThread extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				sleep((int) (Math.random() * 100));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Session session = HibernateUtil.getSessionFactory()
					.getCurrentSession();
			session.beginTransaction();

			int curValue = 0;
			MaxId maxid = new MaxId();
			maxid.setIdType("journal");
			maxid.setIdPrefix("20170714");

			MaxId oldId = (MaxId) session.get(MaxId.class,maxid.getIdType(),
					LockOptions.UPGRADE);

			if (oldId == null) {
				// 为了防止写第一条的时候，重复插入，锁住同一类型的所有记录
				// !此方法不可行，在保存第一条记录时，如第一条记录是不存在的
				Query query = session
						.createQuery("from MaxId where idType =:type and idPrefix >= :prefix order by idPrefix desc");
				query.setParameter("type", "journal");
				query.setParameter("prefix", "20170713");
				query.setLockOptions(LockOptions.UPGRADE);
				List<MaxId> l = query.list();
				System.out.println(this.getName() + "number of records : " + l.size());
				MaxId oldMax = l.size() > 0 ? l.get(0) : null;
				if (oldMax == null
						|| !oldMax.getIdPrefix().equals(maxid.getIdPrefix())) {
					curValue = 1;
					maxid.setCurValue(1);
					session.getTransaction().commit();
				
					session.save(maxid);
				} else {
					curValue = oldMax.getCurValue() + 1;
					oldMax.setCurValue(curValue);
					maxid = oldMax;
					session.update(oldMax);
				}

			} else {
				curValue = oldId.getCurValue() + 1;
				oldId.setCurValue(curValue);
				maxid = oldId;
				session.update(oldId);
			}

			session.getTransaction().commit();
			System.out.println(this.getName() + " generate id : " + maxid);
		}
	}
}
