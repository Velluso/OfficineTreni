package dao;

import java.util.List;

import bean.*;

public interface UtenteDAO {
	   
	   public Integer create(Utente bean);
	   public void update(Utente bean );
	   public void delete(Utente bean );
	   public List<Utente> find(String id);  
	   
	   
}
