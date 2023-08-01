package dao;


import bean.*;

public class UtenteDAOImpl extends BaseDAO implements UtenteDAO {
	   
   public Object create(Utente bean){
	   return super.create(bean);
   }
   
   public void update(Utente bean ){
	   super.update(bean);
   }
	   
   public void delete(Utente bean ){
	   super.delete(bean);
   }

   @Override
   public Utente find(Integer id) {
	   return (Utente)super.find(Utente.class,id);
   }
	   
}
