package dao;

import bean.*;

public interface VagoneDAO {
	   
	   public Object create(Vagone bean);
	   public void update(Vagone bean );
	   public void delete(Vagone bean );
	   public Vagone find(Integer id);  
	   
	   
}
