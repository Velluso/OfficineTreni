package controller;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.LazySingletonContext;

public class BaseController {
	
	protected static SessionFactory factory; 
	 
	static {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}
	ClassPathXmlApplicationContext context = LazySingletonContext.getInstance();
}
