package controller;


import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import bean.*;
import builder.GenericBuilder;
import eccezioni.*;
import service.*;

@Controller
public class HomeController extends BaseController{
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home2";	
	}
	
	@RequestMapping(value = "/costruisciTreno", method = RequestMethod.POST)
	public String user(String siglaTreno, String marchio, Model model) {
		
		System.out.println("Crea Treno page Request");
		System.out.println(marchio + " " + siglaTreno);
		
		TrenoService ts= (TrenoService) context.getBean("TrenoService");			
		VagoneService vs= (VagoneService) context.getBean("VagoneService");
		OrdineService os= (OrdineService) context.getBean("OrdineService");
		GenericBuilder gb = (GenericBuilder) context.getBean("GenericBuilder");
		Treno t=(Treno) context.getBean("Treno");
		
		/*UtenteService us= (UtenteService) context.getBean("UtenteService");				//creazione di un utente
		Utente u=new Utente();					
		u.setCognome("Rossi");
		u.setNome("Mario");
		u.setUsername("pluto");
		us.create(u);*/
																					//COSTRUZIONE TRENO
		try {
			t=gb.costruisciTreno(siglaTreno,marchio,"nomeTreno");
		} catch (VagoneNonRiconosciutoException | VagoniIncompatibiliException | LocomotivaException
				| PosizioneRistoranteException | PesoTrainatoException e) {
			e.printStackTrace();
		}
		System.out.println(t+"    "+t.getTreno().toString());
		
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
	    }
	    model.addAttribute("messaggio",t.getTreno().toString());
		//model.addAttribute("userName", t.checkOrder(siglaTreno, marchio));			gestione eccezioni .... da vedere
		return "trenoCostruito";
	}
	
	@GetMapping("/visualizzaTreno")
	public String visualizzaTreno(@RequestParam String idTreno,Model model) {
		
		Treno t=(Treno) context.getBean("Treno");
		TrenoService ts = (TrenoService) context.getBean("TrenoService");
		VagoneService vs= (VagoneService) context.getBean("VagoneService");
		
		int id=Integer.parseInt(idTreno);
		t=ts.find(id);
		List<Vagone> v= ts.cercaVagoniDiUnTreno(idTreno);			//recupera vagoni del treno	(WHERE id==idTreno)		...da fare
		model.addAttribute("listaTreno",t.getTreno().toString()+v.toString());
		return "visualizzaTreno"; 
					
	}
	
	@GetMapping("/cercaTreno")
	public String viewTreno() {
		return "richiediTreno";
	}
	
	@GetMapping("/cercaOrdini")
	public String viewOrdini() {
		return "visualizzaOrdini";
	}
	
}
