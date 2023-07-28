package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import bean.Bean;

public abstract class BaseDAO {
	private static SessionFactory factory; 
	 
	static {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}
	
	protected Object create(Bean bean){
		
		Session session = factory.openSession();
	    Transaction tx = null;
	    Object ID = null;
	      
	    try {
	    	tx = session.beginTransaction();
	        ID = session.save(bean);
	        tx.commit();
	    } catch (HibernateException e) {
	    	if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
	    } finally {
	    	session.close(); 
	    }
	    return ID;
	}
	   
	   
	protected void update(Bean bean){
		Session session = factory.openSession();
		Transaction tx = null;
	      
		try {
			tx = session.beginTransaction();
	        session.update(bean); 
	        tx.commit();
	    } catch (HibernateException e) {
	        if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
	    } finally {
	        session.close(); 
	    }
	}
	   
	   
	protected void delete(Bean bean){
	    Session session = factory.openSession();
	    Transaction tx = null;
	      
	    try {
	    	tx = session.beginTransaction();
	        session.delete(bean); 
	        tx.commit();
	    } catch (HibernateException e) {
	        if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
	    } finally {
	        session.close(); 
	    }
	}
	    
	    
	protected Bean find(Class classe, Integer id){
		Session session = factory.openSession();
		Transaction tx = null;
		Bean bean = null;
		      
		try {
		  	tx = session.beginTransaction();
		    bean = (Bean) session.get(classe, id);
		    tx.commit();
		} catch (HibernateException e) {
		    if (tx!=null) tx.rollback();
		    	e.printStackTrace(); 
		} finally {
		    session.close(); 
		}
		return bean;
	} 

	protected Bean find(Class classe, String username){
		Session session = factory.openSession();
		Transaction tx = null;
		Bean bean = null;
		      
		try {
		  	tx = session.beginTransaction();
		    bean = (Bean) session.get(classe, username);
		    tx.commit();
		} catch (HibernateException e) {
		    if (tx!=null) tx.rollback();
		    	e.printStackTrace(); 
		} finally {
		    session.close(); 
		}
		return bean;
	} 
	    
	   
}
