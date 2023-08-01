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
public class HomeController extends BaseController{

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		return "redirect:/public/home";
	
	}
	
	//CREA TRENO
	@RequestMapping(value = "/user/viewCostruzione/creaTreno", method = RequestMethod.GET)
	public @ResponseBody String buildAJAXRequest(
			@RequestParam("siglaTreno") String siglaTreno,  @RequestParam("marchio") String marchio, 
	        @RequestParam("nomeTreno") String nomeTreno, HttpSession httpsession) {
		
			Treno t=(Treno) context.getBean("Treno");
			TrenoService ts = (TrenoService) context.getBean("TrenoService");
			Gson gson = new Gson();
			
			try {
				GenericBuilder gb = (GenericBuilder) context.getBean("GenericBuilder");
				t = gb.costruisciTreno(siglaTreno, marchio,nomeTreno);
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
				o.setUsername((String)httpsession.getAttribute("username"));
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
				System.out.println("uno: "+e.getMessage());
				JSONObject obj=new JSONObject();    
				obj.put("errore", e.getMessage()); 
				System.out.println("due: "+gson.toJson(obj));
				return gson.toJson(obj);
			}
			System.out.print(gson.toJson(t)); 
		    return gson.toJson(t);
				
	}
	
	//DATI TRENO DATO ID
	@RequestMapping(value = "/user/viewTreno/findTreno", method = RequestMethod.GET)
	public @ResponseBody String findAJAXRequest( @RequestParam("idTreno") String idTreno, HttpSession session) {
            
			Treno t=(Treno) context.getBean("Treno");
			TrenoService ts = (TrenoService) context.getBean("TrenoService");
			int id=Integer.parseInt(idTreno);
			Gson gson = new Gson();
			System.out.println(idTreno);
			try {
			
				System.out.println("Cerco treno con id: " + idTreno);
				t=ts.find(id);		
				if(t==null) {
					throw new Exception("Treno non presente sul database");
					
				}
				session.setAttribute("idCercato", idTreno);
				System.out.println("Salavata idTreno in session: idCercato = " + session.getAttribute("idCercato").toString());
				session.setAttribute("sigla", t.getSigla());
				System.out.println("Salavata sigla del treno in session: sigla = " + session.getAttribute("sigla").toString());
				
			} catch(Exception e) {
				System.out.println(e.getMessage());
				JSONObject obj=new JSONObject();    
				obj.put("errore", e.getMessage()); 
				System.out.print(obj); 
				return gson.toJson(obj);
			}
			session.setAttribute("sigla", t.getSigla());
	        return gson.toJson(t);
    }

	//DATI VAGONI DATO ID TRENO
	/*@RequestMapping(value = "/user/viewVagoni/findVagoni", method = RequestMethod.GET)
	public @ResponseBody String viewVagoniAJAXRequest( @RequestParam("username") String idTreno) {
		
			List<Vagone> vagoni = new ArrayList<>();
			TrenoService ts = (TrenoService) context.getBean("TrenoService");
			int id=Integer.parseInt(idTreno);
			Gson gson = new Gson();
			
			try {
			
				System.out.println("Cerco treno con id: " + idTreno);
				vagoni=ts.cercaVagoniDiUnTreno(idTreno);
				
				
				
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
	    }*/
	
	//VISTA ORDINI
	@RequestMapping(value = "/user/cercaOrdini/findOrdini", method = RequestMethod.GET)
	public @ResponseBody String ordiniAJAXRequest( @RequestParam("username") String username, HttpSession session) {
		
			List<String> authorities = (List<String>) session.getAttribute("authorities");
	       	List<Ordine> o= new ArrayList<Ordine>();
			OrdineService or = (OrdineService) context.getBean("OrdineService");
			//System.out.println("Cerco ordini di user:  " + username);
			//o=or.find(username);
			if (authorities!=null && username.equals("admin")){
				System.out.println("Cerco ordini per ADMIN");
				o=or.getAllOrdini();
				System.out.println(o.size()+" ordini trovati");
			}else{
				System.out.println("Cerco ordini di user:  " + (String)session.getAttribute("username"));
				o=or.find((String)session.getAttribute("username"));
				System.out.println(o.size()+" ordini trovati");
			}
			Gson gson = new Gson();
	        return gson.toJson(o);
	   
	}
	
	//VISTA VAGONI
	@RequestMapping(value = "/user/viewVagoni/findVagoni", method = RequestMethod.GET)
	public @ResponseBody String vagoniAJAXRequest(HttpSession session) {
	           
		List<Vagone> v= new ArrayList<Vagone>();
		TrenoService ts= new TrenoService();
		//int id = Integer.parseInt(session.getAttribute("idCercato").toString());
			
		System.out.println("Cerco vagoni di treno: " + session.getAttribute("idCercato").toString());
	    v = ts.cercaVagoniDiUnTreno(session.getAttribute("idCercato").toString());
	    System.out.println(v.size());
		Gson gson = new Gson();
		System.out.println(gson.toJson(v));
		for(Vagone vag:v) {
			switch(vag.getClass().toString()){
				case "class bean.ItaloLocomotiva":			vag.setTipo("Locomotiva");	break;
				case "class bean.FrecciarossaLocomotiva":	vag.setTipo("Locomotiva");	break;
				case "class bean.TreNordLocomotiva":		vag.setTipo("Locomotiva");break;
				case "class bean.ItaloPasseggeri":			vag.setTipo("Passeggeri");break;
				case "class bean.FrecciarossaPasseggeri":	vag.setTipo("Passeggeri");break;
				case "class bean.TreNordPasseggeri":		vag.setTipo("Passeggeri");break;
				case "class bean.ItaloCargo":				vag.setTipo("Cargo");break;
				case "class bean.FrecciarossaCargo":		vag.setTipo("Cargo");break;
				case "class bean.TreNordCargo":				vag.setTipo("Cargo");break;
				case "class bean.ItaloRistorante":			vag.setTipo("Ristorante");break;
				case "class bean.TreNordRistorante":		vag.setTipo("Ristorante");break;
				case "class bean.FrecciarossaRistorante":	vag.setTipo("Ristorante");break;
				default:break;
			}
			System.out.println(vag.getClass().toString());
		}
	    return gson.toJson(v);
	}
	
	@GetMapping("/user/viewCostruzione")
	public String viewCostruzione(HttpSession session) {
		
		List<String> authorities = (List<String>) session.getAttribute("authorities");
        if (authorities != null && (authorities.contains("ADMIN") || authorities.contains("USER"))) {
			return "creaTreno";
		} else {
            return "redirect:/public/errore";
        }
	}
	
	@GetMapping("/user/visualizzaTreno")
	public String viewTreno(HttpSession session) {
		
		List<String> authorities = (List<String>) session.getAttribute("authorities");
        if (authorities != null && (authorities.contains("ADMIN") || authorities.contains("USER"))) {
        	return "visualizzaTreno";
        } else {
            return "redirect:/public/errore";
        }
	}
	
	@GetMapping("/user/cercaOrdini")
	public String viewOrdini(HttpSession session) {
		
		List<String> authorities = (List<String>) session.getAttribute("authorities");
        if (authorities != null && (authorities.contains("ADMIN") || authorities.contains("USER"))) {
        	return "visualizzaOrdini";
		} else {
            return "redirect:/public/errore";
        }
	}
	
	@GetMapping("/user/viewVagoni")
	public String viewVagoni(HttpSession session) {
		
		List<String> authorities = (List<String>) session.getAttribute("authorities");
        if (authorities != null && (authorities.contains("ADMIN") || authorities.contains("USER"))) {
        	return "visualizzaVagoni";
	    } else {
            return "redirect:/public/errore";
        }
	}

}