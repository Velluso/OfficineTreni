package bean;

import java.io.Serializable;

public abstract class Vagone implements Serializable,Bean{
	
	private int id;
	private int peso;
	private int prezzo;
	private int lunghezza;
	private String compagnia;
	private int idTreno;
	private int posizione;
	private String tipo;
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Vagone(int peso, int prezzo, int lunghezza, String compagnia) {
		super();
		this.peso = peso;
		this.prezzo = prezzo;
		this.lunghezza = lunghezza;
		this.compagnia = compagnia;
	}

	public Vagone() {
		super();
	}
	
	public int getPosizione() {
		return posizione;
	}

	public void setPosizione(int posizione) {
		this.posizione = posizione;
	}

	public int getIdTreno() {
		return idTreno;
	}

	public void setIdTreno(int idTreno) {
		this.idTreno = idTreno;
	}

	public String getCompagnia() {
		return compagnia;
	}

	public void setCompagnia(String compagnia) {
		this.compagnia = compagnia;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public int getLunghezza() {
		return lunghezza;
	}

	public void setLunghezza(int lunghezza) {
		this.lunghezza = lunghezza;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
		
}
