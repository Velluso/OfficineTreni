package com.journaldev.spring.controller;

import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import bean.Treno;
import bean.Utente;
import builder.GenericBuilder;
import service.LazySingletonContext;
import service.OrdineService;
import service.TrenoService;
import service.UserServiceJDBC;
import service.UserServiceJDBCImpl;
import service.VagoneService;

@Controller
public class AuthController extends BaseController{
	
	private JdbcTemplate jdbcTemplate;
	private UserServiceJDBC userService;
	private PasswordEncoder passwordEncoder;
	
    public AuthController() {
    	this.jdbcTemplate= (JdbcTemplate) context.getBean("jdbcTemplate");
    	this.passwordEncoder= new BCryptPasswordEncoder();
    	this.userService=new UserServiceJDBCImpl(this.jdbcTemplate,this.passwordEncoder);
    }

    @GetMapping("/public/home")
    public String home() {
        return "home";
    }

    @GetMapping("/public/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/public/loginData")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes) {

        // Validazione dell'username lato server
        if (!username.matches("[a-zA-Z0-9]+")) {
            redirectAttributes.addFlashAttribute("errorMessage", "L'username deve contenere solo caratteri alfanumerici.");
            return "redirect:/public/login";
        } 

        String query = "SELECT username, budget, password FROM utente WHERE username=?";
        List<Map<String, Object>> users = jdbcTemplate.queryForList(query, username);

        if (!users.isEmpty()) {
            Map<String, Object> user = users.get(0);
            String dbUsername = (String) user.get("username");
            double dbBudget = (double) user.get("budget");
            String dbPasswordHash = (String) user.get("password");

            if (passwordEncoder.matches(password, dbPasswordHash)) {
                List<String> authorities = userService.getAuthoritiesForUser(username);
                session.setAttribute("username", username);
                session.setAttribute("authorities", authorities);
                session.setAttribute("budget", dbBudget);
                
                // Controlla se l'utente è ADMIN e reindirizzalo alla pagina di amministrazione
                if (authorities.contains("ADMIN")) {
                    return "redirect:/admin/administrator";
                } else {
                    return "redirect:/public/home";
                }
            }
        }
        
        return "redirect:/public/login";
    }
    

    @GetMapping("/public/access_denied")
    public String accessDenied() {
        return "access_denied";
    }
    
    @GetMapping("/public/errore")
    public String getErrore() {
        return "errore";
    }
    
    
    @GetMapping("/public/registrazione")
    public String showRegistrationForm() {
        return "registrazione";
    }

    
    @PostMapping("/public/registrazioneData")
    public String registerUser(
            @RequestParam String nome,
            @RequestParam String cognome,
            @RequestParam String email,     
            @RequestParam String username, 
            @RequestParam String password, 
            HttpSession session,
            RedirectAttributes redirectAttributes) {
    	
    	
// Controlla se l'username e la password soddisfano i requisiti delle regex
    	
    	if (!isValidName(nome) || !isValidName(cognome)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Il nome e il cognome devono contenere solo caratteri alfabetici.");
            return "redirect:/public/registrazione";
        }
    	
        if (!isValidUsername(username)) {
            redirectAttributes.addFlashAttribute("errorMessage", "L'username deve contenere solo caratteri alfanumerici.");
            return "redirect:/public/registrazione";
        }

        if (!isValidPassword(password)) {
            redirectAttributes.addFlashAttribute("errorMessage", "La password deve essere di almeno 6 caratteri e contenere almeno una lettera, un numero e un carattere speciale.");
            return "redirect:/public/registrazione";
        }

        // Controlla se l'email soddisfa i requisiti della regex
        if (!isValidEmail(email)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Inserisci un indirizzo email valido.");
            return "redirect:/public/registrazione";
        }
    	
    	

        // Crea un nuovo utente e salvalo nel database utilizzando il service
        Utente newUser = new Utente(nome, cognome, 0.0, email, username, password, "USER");
        try {
            userService.saveUtente(newUser);

            // Dopo la registrazione, crea una sessione per l'utente
            List<String> authorities = userService.getAuthoritiesForUser(username);
            session.setAttribute("username", username);
            session.setAttribute("authorities", authorities);

        } catch (RuntimeException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("errorMessage", "Username già in uso.");
            return "redirect:/public/registrazione";
        }

        return "redirect:/public/home";
    }
    
    
    
 // validazione input registrazione lato server DA SPOSTARE
    
    private boolean isValidUsername(String username) {
        // Regex per l'username (caratteri alfanumerici)
        String regex = "^[a-zA-Z0-9]+$";
        return username.matches(regex);
    }

    private boolean isValidPassword(String password) {
        // Regex per la password (almeno 6 caratteri, una lettera, un numero e un carattere speciale)
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{6,}$";
        return password.matches(regex);
    }

    private boolean isValidEmail(String email) {
        // Regex per l'email (valido formato email)
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(regex);
    }
    
    
    private boolean isValidName(String name) {
        // Regex per il nome e cognome (caratteri alfabetici)
        String regex = "^[a-zA-Z]+$";
        return name.matches(regex);
    }
    
    /*    */
    
    
    
    
    
    @GetMapping("/admin/administrator")
    public String admin(HttpSession session, Model model) {
    	
        List<String> authorities = (List<String>) session.getAttribute("authorities");

        
        if (authorities != null && authorities.contains("ADMIN")) {
           
            return "admin";
        } else {
           
            return "redirect:/public/access_denied";
        }
    }

   
    @GetMapping("/admin/ordini")
    public String ordini(HttpSession session, Model model) {
    	
        List<String> authorities = (List<String>) session.getAttribute("authorities");

        
        if (authorities != null && authorities.contains("ADMIN")) {
           
            return "ordini";
        } else {
           
            return "redirect:/public/access_denied";
        }
    }
    
    
    /* SISTEARE */
    
    @GetMapping("/user/ordiniutente")
    public String ordiniUtente(HttpSession session, Model model) {
    	
        List<String> authorities = (List<String>) session.getAttribute("authorities");

        
        if (authorities != null && authorities.contains("USER")) {
           
            return "ordiniUtente";
        } else {
           
            return "redirect:/public/access_denied";
        }
    }
    
    
    
    
    
    
    
    
    /* */
    
    
    @PostMapping("/logout")
    public String logout(HttpSession session) throws ServletException {
       session.invalidate();
        return "redirect:/public/login?logout"; // Reindirizza alla pagina di login con il parametro "logout" per mostrare un messaggio di logout avvenuto con successo.
    }
    
	
}