package service;

import java.util.List;

import bean.*;
import dao.*;

public class OrdineService {
	
	private OrdineDAO dao = new OrdineDAOImpl(); 
		
	public void create (Ordine ordine) {
		Object id = dao.create(ordine); 
		System.out.println("creato l'ordine con idTreno: " + ordine.getIdTreno() +" e idUtente: "+ ordine.getUsername());
	} 
	
	public void update (Ordine ordine) {
		dao.update(ordine); 
		System.out.println("modificato l'ordine con idTreno: " + ordine.getIdTreno() +" e idUtente: "+ ordine.getUsername());
	} 	

	public Ordine find(int id) {
		return dao.find(id);
	} 
	
	public List<Ordine> find(String username) {
		return dao.find(username);
	}

	public List<Ordine> getAllOrdini() {
		return dao.getAllOrdini();
	}
}
