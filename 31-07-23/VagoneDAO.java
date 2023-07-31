package dao;

import java.util.List;

import bean.*;

public interface VagoneDAO {
	   
	   public Integer create(Vagone bean);
	   public void update(Vagone bean );
	   public void delete(Vagone bean );
	   public Vagone find(Integer id);
	   public List<Vagone> findByTrenoId(int id);  
	   
	   
}
