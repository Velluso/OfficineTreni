package bean;

import java.io.Serializable;
import java.util.Date;

public class Ordine implements Serializable, Bean {
	
	private int idOrdine;
	private int idTreno;
	private String username;
	private Date dataCreazione;
	private Date dataConclusione;
	private String stato;
	
	public Ordine() {}
	   
	public Ordine(int idTreno, String username, Date dataCreazione, Date dataConclusione, String stato) {
		this.idTreno = idTreno;
		this.username = username;
		this.dataCreazione = dataCreazione;
		this.dataConclusione = dataConclusione;
		this.stato = stato;
	}

	public int getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(int idOrdine) {
		this.idOrdine = idOrdine;
	}

	public int getIdTreno() {
		return idTreno;
	}

	public void setIdTreno(int idTreno) {
		this.idTreno = idTreno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDataConclusione() {
		return dataConclusione;
	}

	public void setDataConclusione(Date dataConclusione) {
		this.dataConclusione = dataConclusione;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}
	
	
	
	
	
}