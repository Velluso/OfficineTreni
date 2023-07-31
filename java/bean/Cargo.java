package bean;

public abstract class Cargo extends Vagone {

	private int capienzaPeso;
	
	public Cargo() {
		super();
	}
	
	public Cargo(int peso, int prezzo, int lunghezza, String compagnia, int capienzaPeso) {
		super(peso, prezzo, lunghezza, compagnia);
		this.capienzaPeso=capienzaPeso;
	}

	public int getCapienzaPeso() {
		return capienzaPeso;
	}

	public void setCapienzaPeso(int capienzaPeso) {
		this.capienzaPeso = capienzaPeso;
	}

	@Override
	public String toString() {
		return "Cargo [capienzaPeso=" + capienzaPeso + "]";
	}

}
