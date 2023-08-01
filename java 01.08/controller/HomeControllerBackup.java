/*package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
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
public class HomeControllerFede extends BaseController{
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "redirect:/public/home";	
	}
	
	//CREA TRENO
	//TODO aggiungere info sulla posizione del vagone
	@RequestMapping(value = "user/creaTreno", method = RequestMethod.GET)
	public @ResponseBody String buildAJAXRequest(
	            @RequestParam("siglaTreno") String siglaTreno,  @RequestParam("marchio") String marchio, @RequestParam("nomeTreno") String nomeTreno) {
		
			Treno t=(Treno) context.getBean("Treno");
			TrenoService ts = (TrenoService) context.getBean("TrenoService");
			Gson gson = new Gson();
			
			try {
					GenericBuilder gb = (GenericBuilder) context.getBean("GenericBuilder");
					t = gb.costruisciTreno(siglaTreno, marchio, nomeTreno);
					//crea treno
					t.setStato("in costruzione");
					ts.crea(t);
					VagoneService vs= (VagoneService) context.getBean("VagoneService");
					OrdineService os= new OrdineService();
					Ordine o = new Ordine();
					//crea vagoni
					
					for(Vagone v : t.getTreno()) {
						
						v.setIdTreno(t.getIdTreno());
						vs.create(v);
					}
					
					//crea ordine
					o.setIdTreno(t.getIdTreno());
					o.setStato(t.getStato());
					o.setUsername("federico.mascali");
					o.setDataCreazione(Date.valueOf(LocalDate.now()));
					os.create(o);
					
					//ultima il treno e l'ordine
					t.setStato("ultimato");
					o.setStato(t.getStato());
					o.setDataConclusione(Date.valueOf(LocalDate.now()));
					ts.update(t);
					os.update(o);
					
			}catch(VagoneNonRiconosciutoException | VagoniIncompatibiliException |LocomotivaException | PosizioneRistoranteException | PesoTrainatoException e){
				System.out.println(e.getMessage());
				JSONObject obj=new JSONObject();    
				obj.put("errore", e.getMessage()); 
				System.out.print(obj); 
				return gson.toJson(obj);
			}
	        return gson.toJson(t);
	    }
	
	//DATI TRENO DATO ID
	@RequestMapping(value = "user/findTreno", method = RequestMethod.GET)
	public @ResponseBody String findAJAXRequest( @RequestParam("idTreno") String idTreno) {
		
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
		@RequestMapping(value = "/getViewVagoni", method = RequestMethod.GET)
	public @ResponseBody String viewVagoniAJAXRequest( @RequestParam("idTreno") String idTreno) {
			
				ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springBeansTreno.xml");
				List<Vagone> vagoni = new ArrayList<>();
				TrenoService ts = (TrenoService) context.getBean("TrenoService");
				int id=Integer.parseInt(idTreno);
				Gson gson = new Gson();
				
				try {
				
					System.out.println("Cerco treno con id: " + idTreno);
					vagoni=ts.cercaVagoniDiUnTreno(idTreno);
					
					for(int i=0; i<vagoni.size(); i++) {
						System.out.println(vagoni.get(i).getCompagnia());
						System.out.println(vagoni.get(i).getPeso());
						System.out.println(vagoni.get(i).getId());
						System.out.println(vagoni.get(i).getIdTreno());
						//System.out.println(vagoni.get(i).getTipoVagone());
					}
					
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
	@RequestMapping(value = "user/visualizzaOrdini", method = RequestMethod.GET)
	public @ResponseBody String ordiniAJAXRequest( @RequestParam("username") String username) {
		
			List<Ordine> o= new ArrayList<Ordine>();
			OrdineService or = (OrdineService) context.getBean("OrdineService");
			System.out.println("Cerco ordini di user:  " + username);
			o=or.find(username);		
			System.out.println(o.size());
			Gson gson = new Gson();
	        return gson.toJson(o);
	    }
	
	
	@GetMapping("user/creaTrenoView")
	public String viewCostruzione() {
		return "creaTreno";
	}
	
	@GetMapping("user/findTrenoView")
	public String viewTreno() {
		return "visualizzaTreno";
	}
	
	@GetMapping("user/visualizzaOrdiniView")
	public String viewOrdini() {
		return "visualizzaOrdini";
	}
	
	@GetMapping("/home")
	public String viewHome() {
		return "redirect:/public/home";
	}
	
}*/
package controller;


