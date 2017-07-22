package com.test.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.entry.BookClassify;
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
        BookClassify b = (BookClassify)session.get(BookClassify.class, 1);
        System.out.println(b.getClassifyName());
        System.out.println(b.getDescription());
        System.out.println(b.getCreateDate());
        session.getTransaction().commit();
    }
    
    @Test
    public void saveUser(){
    	Session session = getSession();
        session.beginTransaction();
        
        BookClassify classify = new BookClassify();
        classify.setClassifyName("测试");
        classify.setDescription("测试1");
        classify.setCreateDate(new Date());
        classify.setLastModifyDate(new Date());
        session.save(classify);
        session.getTransaction().commit();
    }
}
