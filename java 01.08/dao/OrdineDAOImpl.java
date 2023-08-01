package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import bean.Ordine;
import bean.Vagone;

public class OrdineDAOImpl extends BaseDAO implements OrdineDAO {

    public Object create(Ordine bean){
        return super.create(bean);
    }

    public void update(Ordine bean ){
        super.update(bean);
    }

    public void delete(Ordine bean ){	//da implementare
        super.update(bean);
    }

    public Ordine find(Integer id) {
        return (Ordine) super.find(Ordine.class,id);
    }

	public List<Ordine> find(String username) {
		Session session = factory.openSession();
	    Transaction tx = null;
	    List<Ordine> results=null;
	    try {
	    	tx=session.beginTransaction();
	    	TypedQuery<Ordine> query = session.getNamedQuery("ordiniDatoUsername");    
            query.setParameter("username",username);   
            results=query.getResultList(); 
            System.out.println(results.toString());
	    	tx.commit();
	    } catch (HibernateException e) {
	    	if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
	    } finally {
	    	session.close(); 
	    }
	    
	    return results;
	}

	@Override
	public List<Ordine> getAllOrdini() {
		Session session = factory.openSession();
	    Transaction tx = null;
	    List<Ordine> results=null;
	    try {
	    	tx=session.beginTransaction();
	    	TypedQuery<Ordine> query = session.getNamedQuery("ordiniTutti");
            results=query.getResultList(); 
            System.out.println(results.toString());
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

