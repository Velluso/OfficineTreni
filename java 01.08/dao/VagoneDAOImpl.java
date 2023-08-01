package dao;


import bean.*;

public class VagoneDAOImpl extends BaseDAO implements VagoneDAO {
	   
   public Object create(Vagone bean){
	   return super.create(bean);
   }
   
   public void update(Vagone bean ){
	   super.update(bean);
   }
	   
   public void delete(Vagone bean ){
	   super.delete(bean);
   }

   public Vagone find(Integer id) {
	   return (Vagone)super.find(Vagone.class,id);
   }
	   
}
