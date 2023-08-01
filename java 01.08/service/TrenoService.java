package service;

import java.util.List;

import bean.Treno;
import bean.Vagone;
import dao.TrenoDAOImpl;
import dao.TrenoDAO;
import builder.GenericBuilder;


public class TrenoService {

	private TrenoDAO dao = new TrenoDAOImpl();

    public void crea (Treno treno) {
    	Object id = dao.create(treno);
        System.out.println("creato il treno con id: " + id);
    }

    public void update (Treno treno) {
        dao.update(treno);
        System.out.println("modificato il treno con id: " + treno.getIdTreno());
    }

    public void cambiaStato (Treno treno) {

        treno.setStato("pronto");
        dao.update(treno);
        System.out.println("modificato il treno con id: " + treno.getIdTreno());
    }

    public Treno find(int id) {
    	return dao.find(id);
    }

	public TrenoService() {}
    
	public List<Vagone> cercaVagoniDiUnTreno(String idTreno){
		return dao.vagoniDiUnTreno(idTreno);
	}
	
}

