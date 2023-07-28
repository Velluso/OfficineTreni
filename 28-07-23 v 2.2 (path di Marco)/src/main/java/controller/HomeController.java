package controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import bean.Ordine;
import bean.Treno;
import bean.Utente;
import bean.Vagone;
import builder.GenericBuilder;
import eccezioni.LocomotivaException;
import eccezioni.PesoTrainatoException;
import eccezioni.PosizioneRistoranteException;
import eccezioni.VagoneNonRiconosciutoException;
import eccezioni.VagoniIncompatibiliException;
import service.OrdineService;
import service.TrenoService;
import service.UtenteService;
import service.VagoneService;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "redirect:/public/home";
	
	}
	
	//CREA TRENO
	@RequestMapping(value = "/user/viewCostruzione/creaTreno", method = RequestMethod.GET)
	public @ResponseBody String buildAJAXRequest(
	            @RequestParam("siglaTreno") String siglaTreno,  @RequestParam("marchio") String marchio, 
	            @RequestParam("nomeTreno") String nomeTreno, HttpSession httpsession) {
		
				ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springBeansTreno.xml");
				Treno t=(Treno) context.getBean("Treno");
				TrenoService ts = (TrenoService) context.getBean("TrenoService");
				Gson gson = new Gson();
				
				try {
	
						GenericBuilder gb = (GenericBuilder) context.getBean("GenericBuilder");
						t = gb.costruisciTreno(siglaTreno, marchio);
						//crea treno
						t.setSigla(siglaTreno);
						t.setStato("in costruzione");
						t.setNome(nomeTreno);
						ts.crea(t);
						VagoneService vs= (VagoneService) context.getBean("VagoneService");
						OrdineService os= new OrdineService();
						Ordine o = new Ordine();
						//crea vagoni
						
						int i = 0;
						
						for(Vagone v : t.getTreno()) {
							i++;
							v.setIdTreno(t.getIdTreno());
							v.setPosizione(i);
							vs.create(v);
						}
						
						//crea ordine
						o.setIdTreno(t.getIdTreno());
						o.setStato(t.getStato());
						o.setUsername("federico.mascali");
						o.setDataCreazione(new Date());
						os.create(o);
						
						//ultima il treno e l'ordine
						t.setStato("ultimato");
						o.setStato(t.getStato());
						o.setDataConclusione(new Date());
						ts.update(t);
						os.update(o);
				
				
				}
				
				catch(VagoneNonRiconosciutoException | VagoniIncompatibiliException |LocomotivaException | PosizioneRistoranteException | PesoTrainatoException e){
					System.out.println(e.getMessage());
					JSONObject obj=new JSONObject();    
					obj.put("errore", e.getMessage()); 
					System.out.print(obj); 
					return gson.toJson(obj);
				}

		        return gson.toJson(t);
			
	    }
	
	//DATI TRENO DATO ID
	@RequestMapping(value = "/user/viewTreno/findTreno", method = RequestMethod.GET)
	public @ResponseBody String findAJAXRequest( @RequestParam("idTreno") String idTreno, HttpSession session) {
            
        	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springBeansTreno.xml");
			Treno t=(Treno) context.getBean("Treno");
			TrenoService ts = (TrenoService) context.getBean("TrenoService");
			int id=Integer.parseInt(idTreno);
			Gson gson = new Gson();
			
			try {
			
				System.out.println("Cerco treno con id: " + idTreno);
				t=ts.find(id);		
				if(t==null) {
					throw new Exception("Treno non presente sul database");
				}
				
			} catch(Exception e) {
				System.out.println(e.getMessage());
				JSONObject obj=new JSONObject();    
				obj.put("errore", e.getMessage()); 
				System.out.print(obj); 
				return gson.toJson(obj);
			}
			
	        return gson.toJson(t);
    }

	//DATI VAGONI DATO ID TRENO
	@RequestMapping(value = "/user/viewTreno/getViewVagoni", method = RequestMethod.GET)
	public @ResponseBody String viewVagoniAJAXRequest( @RequestParam("idTreno") String idTreno) {
		
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springBeansTreno.xml");
			List<Vagone> vagoni = new ArrayList<>();
			TrenoService ts = (TrenoService) context.getBean("TrenoService");
			int id=Integer.parseInt(idTreno);
			Gson gson = new Gson();
			
			try {
			
				System.out.println("Cerco treno con id: " + idTreno);
				vagoni=ts.cercaVagoniDiUnTreno(idTreno);
				
				/* riesce a stampare a video il tipo della classe di vagone 
				 * ma non esiste un getTipoVagone() per cui non riesce ad ottenerlo in JSON
				for(int i=0; i<vagoni.size(); i++) {					
					System.out.println(vagoni.get(i).getClass().getSimpleName());
				}*/
				
				if(vagoni.size()<=0) {
					throw new Exception("Treno non presente sul database");
				}
				
			} catch(Exception e) {
				System.out.println(e.getMessage());
				JSONObject obj=new JSONObject();    
				obj.put("errore", e.getMessage()); 
				System.out.print(obj); 
				return gson.toJson(obj);
			}
			
	        return gson.toJson(vagoni);
	    }
	
	//VISTA ORDINI
	@RequestMapping(value = "/user/viewOrdini/findOrdini", method = RequestMethod.GET)
	public @ResponseBody String ordiniAJAXRequest( @RequestParam("username") String username, HttpSession session) {
            
		
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springBeansOrdine.xml");
			List<Ordine> o= new ArrayList<Ordine>();
			OrdineService or = (OrdineService) context.getBean("OrdineService");
			System.out.println("Cerco ordini di user:  " + username);
			
			//Sceglie se ottenere ordini per l'admin o per un utente semplice
			//La query già create e funzionante ma getRuolo() torna null e non esiste getEnabled()
			//C'è un modo più semplice per capire se è admin invece che inerrogare il DB?
			
			/*List<Utente> u= new ArrayList<Utente>();
			UtenteService us = new UtenteService();
			
			u = us.find(username);
			if(u.get(0).getRuolo().equals("admin")) { //oppure if(u.get(0).getEnabled()){
				o=or.getAll();
			}
			else {*/
				o=or.find(username);
			//}
			System.out.println(o.size());
			Gson gson = new Gson();
	        return gson.toJson(o);
        
        
	}
		
	@GetMapping("/user/viewCostruzione")
	public String viewCostruzione(HttpSession session) {
		
		List<String> authorities = (List<String>) session.getAttribute("authorities");

        if (authorities != null && (authorities.contains("ADMIN") || authorities.contains("USER"))) {
		
		return "creaTreno";
		
        } else {
        	
            // Utente non autorizzato, reindirizza alla pagina di accesso negato
            return "redirect:/public/errore";
        }
	}
	
	@GetMapping("/user/viewTreno")
	public String viewTreno(HttpSession session) {
		
		List<String> authorities = (List<String>) session.getAttribute("authorities");

        if (authorities != null && (authorities.contains("ADMIN") || authorities.contains("USER"))) {
		
		return "visualizzaTreno";
		
        } else {
        	
            // Utente non autorizzato, reindirizza alla pagina di accesso negato
            return "redirect:/public/errore";
        }
	}
	
	@GetMapping("/user/viewOrdini")
	public String viewOrdini(HttpSession session) {
		
		List<String> authorities = (List<String>) session.getAttribute("authorities");

        if (authorities != null && (authorities.contains("ADMIN") || authorities.contains("USER"))) {
		
		return "visualizzaOrdini";
		
        } else {
        	
            // Utente non autorizzato, reindirizza alla pagina di accesso negato
            return "redirect:/public/errore";
        }
	}
	
	/*@GetMapping("/user/viewVagoni")
	public String viewOrdini(HttpSession session) {
		
		List<String> authorities = (List<String>) session.getAttribute("authorities");

        if (authorities != null && (authorities.contains("ADMIN") || authorities.contains("USER"))) {
		
		return "visualizzaVagoni";
		
        } else {
        	
            // Utente non autorizzato, reindirizza alla pagina di accesso negato
            return "redirect:/public/errore";
        }
	}*/
}
