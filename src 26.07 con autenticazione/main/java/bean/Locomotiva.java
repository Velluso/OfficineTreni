package bean;

public abstract class Locomotiva extends Vagone {

	private int pesoTrainante;
	
	public Locomotiva() {
		super();
	}
	
	public Locomotiva(int peso, int prezzo, int lunghezza, String compagnia, int pesoTrainante) {
		super(peso, prezzo, lunghezza, compagnia);
		this.pesoTrainante=pesoTrainante;
	}

	public int getPesoTrainante() {
		return pesoTrainante;
	}

	public void setPesoTrainante(int pesoTrainante) {
		this.pesoTrainante = pesoTrainante;
	}

	@Override
	public String toString() {
		return "Locomotiva [pesoTrainante=" + pesoTrainante + "]";
	}

}
