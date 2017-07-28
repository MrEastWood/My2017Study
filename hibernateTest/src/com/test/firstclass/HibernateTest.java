package com.test.firstclass;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.test.util.HibernateUtil;

public class HibernateTest {
	
	@Test
	public void delAllMaxId(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("delete from MaxId");
		query.executeUpdate();
		session.getTransaction().commit();
	}
	
	@Test
	public void insertMaxId(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		MaxId maxId = new MaxId();
		maxId.setIdType("journal");
		maxId.setIdPrefix("20170728");
		maxId.setCurValue(1);
		session.save(maxId);
		
		session.getTransaction().commit();
	}
	
	/**
	 * �����¼��ͨ��session.save()���������¼
	 */
	@Test
	public void addUser(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		User user = new User();
		user.setName("����");
		
		session.save(user);
		session.getTransaction().commit();
		
	}
	
	/**
	 * ͨ���������Ҽ�¼��session.get()����
	 */
	@Test
	public void getUserById(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user = (User)session.get(User.class, 1);
		System.out.println(user);
		session.getTransaction().commit();
	}
	
	/**
	 * ͨ��HQL����ѯ���������ļ�¼
	 */
	@Test
	public void getAllUser(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<User> result = session.createQuery("from User").list();
		for(User u : result){
			System.out.println(u);
		}
		session.getTransaction().commit();
	}
	
	/**
	 * ɾ����¼��ͨ��Session.delete����ɾ��
	 */
	@Test
	public void delUser(){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User u = new User();
		u.setId(1);
		session.delete(u);
		
		session.getTransaction().commit();
	}
	
	/**
	 * �������ݼ�¼��ͨ��session.get()������ȡ����󣬸��¶���commit
	 */
	@Test
	public void modifyUser(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		User user = (User)session.get(User.class, 2);
		user.setName("����");
		
		session.getTransaction().commit();
	}
	
	/**
	 * ͨ��HQL��������¿���¼ --- һ�θ��¶���
	 */
	@Test
	public void updateUsers(){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String hql = "update User set name =:nameUpd where name=:nameOri";
		Query query = session.createQuery(hql);
		query.setParameter("nameUpd", "����");
		query.setParameter("nameOri", "����");
		
		query.executeUpdate();
		session.getTransaction().commit();
		
	}
}
