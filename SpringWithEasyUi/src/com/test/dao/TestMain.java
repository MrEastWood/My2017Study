package com.test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.entry.User;
@ContextConfiguration(locations= {"classpath*:config/applicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMain {
	
	@Autowired
	protected SessionFactory sessionFactory;
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public Session getNewSession() {
        return sessionFactory.openSession();
    }
	
    public void flush() {
        getSession().flush();
    }
    
    public void clear() {
        getSession().clear();
    }
    @Test
    public void load() {
        Session session = getSession();
        session.beginTransaction();
        User u = (User)session.get(User.class, "100001");
        System.out.println(u.getId());
        System.out.println(u.getName());
        session.getTransaction().commit();
    }
}
