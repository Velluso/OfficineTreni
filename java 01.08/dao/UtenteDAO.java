package dao;

import bean.*;

public interface UtenteDAO {
	   
	   public Object create(Utente bean);
	   public void update(Utente bean );
	   public void delete(Utente bean );
	   public Utente find(Integer id);  
	   
	   
}
