package com.test.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
        	Configuration cfg = new Configuration().configure();
        	StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
        			applySettings(cfg.getProperties()).build();
        	SessionFactory sessionFactory = cfg.buildSessionFactory(serviceRegistry);
        	return sessionFactory;
        	//Hibernate文档中的方法，测试不可用
//        	return new Configuration().configure().buildSessionFactory(
//    			    new StandardServiceRegistryBuilder().build() );
            
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
