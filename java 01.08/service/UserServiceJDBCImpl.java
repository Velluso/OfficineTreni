package service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import bean.Utente;

@Service
public class UserServiceJDBCImpl implements UserServiceJDBC {

	
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public UserServiceJDBCImpl(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void saveUtente(Utente utente) {
    	
        // Verifica se l'utente esiste già nel database per evitare duplicati
        String query = "SELECT COUNT(*) FROM utente WHERE username=?";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class, utente.getUsername());
        if (count != null && count > 0) {
            throw new RuntimeException("Username già in uso.");
        }

        // Crittografa la password prima di salvarla nel database
        String hashPassword = passwordEncoder.encode(utente.getPassword());

        // Inserisci l'utente nel database
        String queryInserimento = "INSERT INTO utente (nome, cognome, email, username, password, budget, enabled) VALUES (?, ?, ?, ?, ?, 0.0, 1)";
        jdbcTemplate.update(queryInserimento, utente.getNome(), utente.getCognome(), utente.getEmail(), utente.getUsername(), hashPassword);

        // Inserisci il ruolo dell'utente nella tabella "authorities"
        String queryAutorizzazioni = "INSERT INTO authorities (username, authority) VALUES (?, ?)";
        jdbcTemplate.update(queryAutorizzazioni, utente.getUsername(), utente.getRuolo());
    }

    
    
    @Transactional(readOnly = true)
    @Override
    public List<String> getAuthoritiesForUser(String username) {
        // Query per ottenere le autorizzazioni dell'utente dal database
        String query = "SELECT authority FROM authorities WHERE username=?";
        List<String> authorities = jdbcTemplate.queryForList(query, String.class, username);
        return authorities;
    }
    
    
    
    
    
}

