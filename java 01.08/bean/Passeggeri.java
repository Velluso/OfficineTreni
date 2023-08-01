package bean;

public abstract class Passeggeri extends Vagone {

	private int postiDisponibili;
	
	public Passeggeri() {
		super();
	}

	public Passeggeri(int peso, int prezzo, int lunghezza, String compagnia, int postiDisponibili) {
		super(peso, prezzo, lunghezza, compagnia);
		this.postiDisponibili=postiDisponibili;
	}

	public int getPostiDisponibili() {
		return postiDisponibili;
	}

	public void setPostiDisponibili(int postiDisponibili) {
		this.postiDisponibili = postiDisponibili;
	}

	@Override
	public String toString() {
		return "Passeggeri [postiDisponibili=" + postiDisponibili + "]";
	}

}
