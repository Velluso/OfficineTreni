package bean;

public abstract class Ristorante extends Vagone {

	private int copertiDisponibili;
	
	public Ristorante() {
		super();
	}

	public Ristorante(int peso, int prezzo, int lunghezza, String compagnia, int copertiDisponibili) {
		super(peso, prezzo, lunghezza, compagnia);
		this.copertiDisponibili=copertiDisponibili;
	}

	public int getCopertiDisponibili() {
		return copertiDisponibili;
	}

	public void setCopertiDisponibili(int copertiDisponibili) {
		this.copertiDisponibili = copertiDisponibili;
	}

	@Override
	public String toString() {
		return "Ristorante [copertiDisponibili=" + copertiDisponibili + "]";
	}
	

}
