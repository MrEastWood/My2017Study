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
	 * 插入记录，通过session.save()方法插入记录
	 */
	@Test
	public void addUser(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		User user = new User();
		user.setName("张三");
		
		session.save(user);
		session.getTransaction().commit();
		
	}
	
	/**
	 * 通过主键查找记录，session.get()方法
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
	 * 通过HQL语句查询符合条件的记录
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
	 * 删除记录，通过Session.delete方法删除
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
	 * 更新数据记录，通过session.get()方法获取对象后，更新对象并commit
	 */
	@Test
	public void modifyUser(){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		User user = (User)session.get(User.class, 2);
		user.setName("李四");
		
		session.getTransaction().commit();
	}
	
	/**
	 * 通过HQL语句来更新库表记录 --- 一次更新多条
	 */
	@Test
	public void updateUsers(){
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		String hql = "update User set name =:nameUpd where name=:nameOri";
		Query query = session.createQuery(hql);
		query.setParameter("nameUpd", "王五");
		query.setParameter("nameOri", "张三");
		
		query.executeUpdate();
		session.getTransaction().commit();
		
	}
}
