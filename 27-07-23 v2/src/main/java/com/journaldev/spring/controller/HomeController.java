package com.journaldev.spring.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
import bean.Vagone;
import builder.GenericBuilder;
import eccezioni.LocomotivaException;
import eccezioni.PesoTrainatoException;
import eccezioni.PosizioneRistoranteException;
import eccezioni.VagoneNonRiconosciutoException;
import eccezioni.VagoniIncompatibiliException;
import service.OrdineService;
import service.TrenoService;
import service.VagoneService;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	
	}
	
	//CREA TRENO
	@RequestMapping(value = "/creaTreno", method = RequestMethod.GET)
	public @ResponseBody String buildAJAXRequest(
	            @RequestParam("siglaTreno") String siglaTreno,  @RequestParam("marchio") String marchio, @RequestParam("nomeTreno") String nomeTreno) {
		
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
					//VagoneService vs= (VagoneService) context.getBean("VagoneService");
					OrdineService os= new OrdineService();
					Ordine o = new Ordine();
					//crea vagoni
					/*for(Vagone v : t.getTreno()) {
						
						vs.create(v);
					}*/
					//crea ordine
					o.setIdTreno(t.getIdTreno());
					o.setStato(t.getStato());
					o.setUsername("federico.mascali");
					o.setDataCreazione(new Date());
					os.create(o);
			
			
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
	@RequestMapping(value = "/findTreno", method = RequestMethod.GET)
	public @ResponseBody String findAJAXRequest( @RequestParam("idTreno") String idTreno) {
		
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
	
	//VISTA ORDINI
	/*TODO Ordine.find cerca tramite id, non stringa username*/
	@RequestMapping(value = "/findOrdini", method = RequestMethod.GET)
	public @ResponseBody String ordiniAJAXRequest( @RequestParam("username") String username) {
		
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springBeansOrdine.xml");
			List<Ordine> o= new ArrayList<Ordine>();
			OrdineService or = (OrdineService) context.getBean("OrdineService");
			o=or.find(username);		
			System.out.println("Cerco ordini di user:  " + username);
			System.out.println("ottenuto ordine "+ username);
			Gson gson = new Gson();
	        return gson.toJson(o);
	    }
	
	/*TODO Collegare alla pagina di logout*/
	@GetMapping("/viewLogout")
	public String viewLogout() {
		return "logout";
	}
	
	@GetMapping("/viewCostruzione")
	public String viewCostruzione() {
		return "creaTreno";
	}
	
	@GetMapping("/viewTreno")
	public String viewTreno() {
		return "visualizzaTreno";
	}
	
	@GetMapping("/viewOrdini")
	public String viewOrdini() {
		return "visualizzaOrdini";
	}
	
	@GetMapping("/home")
	public String viewHome() {
		return "home";
	}
	
}