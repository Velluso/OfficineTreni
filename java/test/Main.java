package test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bean.*;
import builder.GenericBuilder;
import eccezioni.*;
import service.LazySingletonContext;
import service.OrdineService;
import service.TrenoService;
import service.UtenteService;
import service.VagoneService;

public class Main {

	private static SessionFactory factory; 
	 
	static {
		try {
			factory = new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) { 
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex); 
		}
	}
	
	public static void main(String[] args){
			
		
		ClassPathXmlApplicationContext context = LazySingletonContext.getInstance();
		List<Ordine> o=null;
		OrdineService os = (OrdineService) context.getBean("OrdineService");
		os.create(new Ordine(1,"pippo",Date.valueOf(LocalDate.now()),null,"bhooooooo"));
		os.create(new Ordine(2,"pippo",Date.valueOf(LocalDate.now()),null,"bhooo2"));
		o=os.find("pippo");		
		System.out.println(o.toString());
		/*GenericBuilder gb = (GenericBuilder) context.getBean("GenericBuilder");			//prove GenericBuilder+eccezioni
		Treno treno=(Treno) context.getBean("Treno");
		try {
			treno=gb.costruisciTreno("hpprppp");
		} catch (LocomotivaException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		} catch (VagoniIncompatibiliException e) {
			System.out.println(e.toString());
			e.printStackTrace();			
		} catch (PosizioneRistoranteException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		} catch (VagoneNonRiconosciutoException e) {
			System.out.println(e.getMessage()+" Elimina dalla sigla il carattere '"+e.getIntruso()+"'");
			e.printStackTrace();
		} catch (PesoTrainatoException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
		System.out.println(treno);
		*/
		/*UtenteService service = new UtenteService(); 			//prove UtenteService
	    System.out.println(service);	      
	    Utente u = service.find(1);
	    u.setBudget(1850);
	    u.setEmail("prova");
	    service.update(u);*/
		/*VagoneService vs=new VagoneService();					//prove VagoneService
		System.out.println(vs);
		FrecciarossaLocomotiva fl = new FrecciarossaLocomotiva();
		ItaloCargo ic = new ItaloCargo();
		vs.create(fl);
		vs.create(ic);
		System.out.println(vs.find(2));
		fl.setPrezzo(500);
		vs.update(fl);*/
		/*OrdineService os = new OrdineService();					//prove OrdineService
		Ordine o =new Ordine(1,2,LocalDate.of(2023,7,21),LocalDate.of(2025, 1, 1),"bhoo");
		os.crea(o);*/
		/*UtenteService us=new UtenteService();						//prove UtenteService
		Utente u=new Utente();
		u.setCognome("Rossi");
		u.setNome("Mario");
		u.setUsername("pluto");
		us.create(u);*/
		/*TrenoService ts= (TrenoService) context.getBean("TrenoService");			//prove costruzione treno
		VagoneService vs= (VagoneService) context.getBean("VagoneService");
		OrdineService os= (OrdineService) context.getBean("OrdineService");
		GenericBuilder gb = (GenericBuilder) context.getBean("GenericBuilder");
		Treno t=(Treno) context.getBean("Treno");
		try {
			t=gb.costruisciTreno("hpprpp","Italo","nome1");
		} catch (VagoneNonRiconosciutoException | VagoniIncompatibiliException | LocomotivaException
				| PosizioneRistoranteException | PesoTrainatoException e) {
			e.printStackTrace();
		}
		System.out.println(t);
		
		//INSERIMENTO TRENO + VAGONI + ORDINE NEL DB IN TRANSAZIONE
		Session session = factory.openSession();
	    Transaction tx = null;	      
	    try {
	    	tx = session.beginTransaction();
	    	ts.crea(t);
	    	int posizione=1;
			for(Vagone v:t.getTreno()) {
				v.setIdTreno(t.getIdTreno());
				v.setPosizione(posizione);
				posizione++;
				vs.create(v);
			}
			Ordine o=new Ordine(t.getIdTreno(),"pluto",Date.valueOf(LocalDate.now()),null,null);
			os.create(o);
			tx.commit();
	    } catch (HibernateException e) {
	    	if (tx!=null) tx.rollback();
	        e.printStackTrace(); 
	    } finally {
	    	session.close(); 
	    }*/
		
		
		
		
		
	    
	}
}
