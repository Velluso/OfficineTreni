package service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import bean.*;
import dao.*;

public class VagoneService {
	
	private VagoneDAO dao = new VagoneDAOImpl(); 
		
	public void create (Vagone vagone) {
		Object id = dao.create(vagone); 
		System.out.println("creato il vagone con id: " + id);
	} 
	
	public void update (Vagone vagone) {
		dao.update(vagone); 
		System.out.println("modificato il vagone con id: " + vagone.getId());
	} 	

	public Vagone find(int id) {
		return dao.find(id);
	}
	
	public List<Vagone> findByTrenoId(int idTreno) {
		return dao.findByTrenoId(idTreno);
	}
	
}
