package dao;


import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import bean.*;

public class UtenteDAOImpl extends BaseDAO implements UtenteDAO {
	   
	private static SessionFactory factory; 
	static {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}
	
   public Integer create(Utente bean){
	   return super.create(bean);
   }
   
   public void update(Utente bean ){
	   super.update(bean);
   }
	   
   public void delete(Utente bean ){
	   super.delete(bean);
   }
   
   public List<Utente> find(String username) {
   	
   	Session session = factory.openSession();
	    Transaction tx = null;
	    List<Utente> results=null;
	    try {
	    	tx=session.beginTransaction();
	    	TypedQuery<Utente> query = session.getNamedQuery("utenteDatoUsername");    
           query.setParameter("username",username);   
           results=query.getResultList();
           System.out.println(results);
	    	tx.commit();
	    } catch (HibernateException e) {
	    	if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
	    } finally {
	    	session.close(); 
	    }
   	
       return results;
   }
	   
}
