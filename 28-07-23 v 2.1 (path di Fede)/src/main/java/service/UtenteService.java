package service;

import java.util.List;

import bean.*;
import dao.*;

public class UtenteService {
	
	private UtenteDAO dao = new UtenteDAOImpl(); 
		
	public void create (Utente utente) {
		Object id = dao.create(utente); 
		System.out.println("creato l'utente con id: " + id);
	} 
	
	public void update (Utente utente) {
		dao.update(utente); 
		System.out.println("modificato l'utente con id: " + utente.getUsername());
	} 	
	
	public List<Utente> find(String id) {
		return dao.find(id);
	} 
	
	
}
