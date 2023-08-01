package bean;

import java.io.Serializable;

public class Utente implements Serializable,Bean{
	
	private String nome;
	private String cognome;
	private String email;
	private double budget;
	private String username;
	private String password;
	private String ruolo;
	private boolean enabled;
	
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Utente() {
		
	}
	public Utente(String nome, String cognome, double budget, String email, String username, String password,
			String ruolo) {
		this.nome=nome;
		this.cognome=cognome;
		this.budget=budget;
		this.email=email;
		this.username=username;
		this.password=password;
		this.ruolo=ruolo;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getEmail() {
		return email;
	}
	public double getBudget() {
		return budget;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public boolean isEnabled() {
		return enabled;
	}
	@Override
	public String toString() {
		return "Utente [nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", budget=" + budget
				+ ", username=" + username + ", password=" + password + ", ruolo=" + ruolo + ", enabled=" + enabled
				+ "]";
	}
	
	
	
	
}
