package service;

import java.util.List;

import org.springframework.stereotype.Service;

import bean.Utente;

@Service
public interface UserServiceJDBC  {

	void saveUtente(Utente utente);

	List<String> getAuthoritiesForUser(String username);

	

	
	
}
	