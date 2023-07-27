package com.journaldev.spring.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

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
import service.OrdineService;
import service.TrenoService;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "home";
	
	}
	
	@RequestMapping(value = "/creaTreno", method = RequestMethod.GET)
	public @ResponseBody String buildAJAXRequest(
	            @RequestParam("siglaTreno") String siglaTreno,  @RequestParam("marchio") String marchio) {
	       
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springBeansTreno.xml");
			Treno t=(Treno) context.getBean("Treno");
			TrenoService ts = (TrenoService) context.getBean("TrenoService");
			if(ts.checkOrder(siglaTreno, marchio).equalsIgnoreCase("ok")) {
				t.setSigla(siglaTreno);
				t.setStato("in costruzione");
				ts.crea(t);
			}
			System.out.println("Provo a costruire treno " + marchio +"con composizione: " +siglaTreno);
			Gson gson = new Gson();
	        return gson.toJson(t);
	    }
	
	@RequestMapping(value = "/findTreno", method = RequestMethod.GET)
	public @ResponseBody String findAJAXRequest( @RequestParam("idTreno") String idTreno) {
		
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springBeansTreno.xml");
			Treno t=(Treno) context.getBean("Treno");
			TrenoService ts = (TrenoService) context.getBean("TrenoService");
			int id=Integer.parseInt(idTreno);
			t=ts.find(id);		
			System.out.println("Cerco treno con id: " + idTreno);
			Gson gson = new Gson();
	        return gson.toJson(t);
	    }
	
	/*TODO Ordine.find cerca tramite id, non stringa username*/
	@RequestMapping(value = "/findOrdini", method = RequestMethod.GET)
	public @ResponseBody String ordiniAJAXRequest( @RequestParam("username") String username) {
		
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springBeansOrdine.xml");
			Ordine o= new Ordine();
			OrdineService or = (OrdineService) context.getBean("OrdineService");
			o=or.find(1);		
			System.out.println("Cerco ordini di user:  " + username);
			System.out.println("ottenuto ordine "+ o.getUsername());
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
