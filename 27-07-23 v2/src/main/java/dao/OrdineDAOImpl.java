package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import bean.Ordine;

public class OrdineDAOImpl extends BaseDAO implements OrdineDAO {
	
	private static SessionFactory factory; 
	static {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}

    public Object create(Ordine bean){
        return super.create(bean);
    }

    public void update(Ordine bean ){
        super.update(bean);
    }

    public void delete(Ordine bean ){	//da implementare
        super.update(bean);
    }

    public List<Ordine> find(String username) {
    	Session session = factory.openSession();
	    Transaction tx = null;
	    List<Ordine> results=null;
	    try {
	    	tx=session.beginTransaction();
	    	TypedQuery<Ordine> query = session.getNamedQuery("vagoniDelTreno");    
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