package dao;


import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import bean.*;

public class VagoneDAOImpl extends BaseDAO implements VagoneDAO {
	   
	private static SessionFactory factory; 
	static {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}
	
   public Integer create(Vagone bean){
	   return super.create(bean);
   }
   
   public void update(Vagone bean ){
	   super.update(bean);
   }
	   
   public void delete(Vagone bean ){
	   super.delete(bean);
   }

   @Override
   public Vagone find(Integer id) {
	   return (Vagone)super.find(Vagone.class,id);
   }


	@Override
	public List<Vagone> findByTrenoId(int idTreno) {
		Session session = factory.openSession();
	    Transaction tx = null;
	    List<Vagone> results=null;
	    try {
	    	tx=session.beginTransaction();
	    	TypedQuery<Vagone> query = session.getNamedQuery("vagoniByTrenoId");
	    	query.setParameter("idTreno",idTreno); 
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
