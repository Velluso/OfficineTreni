package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import bean.Treno;
import bean.Vagone;

public class TrenoDAOImpl extends BaseDAO implements TrenoDAO {
	
	private static SessionFactory factory; 
	static {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}
	
    public Object create(Treno bean){
        return super.create(bean);
    }

    public void update(Treno bean ){
        super.update(bean);
    }

    public void delete(Treno bean ){	//da implementare
        super.update(bean);
    }

    public Treno find(Integer id) {
        return (Treno) super.find(Treno.class,id);
    }

	public List<Vagone> vagoniDiUnTreno(String idTreno) {
		Session session = factory.openSession();
	    Transaction tx = null;
	    int id=Integer.parseInt(idTreno);
	    List<Vagone> results=null;
	    try {
	    	tx=session.beginTransaction();
	    	TypedQuery<Vagone> query = session.getNamedQuery("vagoniDelTreno");    
            query.setParameter("id",id);   
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

