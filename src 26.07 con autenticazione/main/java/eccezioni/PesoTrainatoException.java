package eccezioni;

import java.io.IOException;

public class PesoTrainatoException extends TrenoException {

	private int pesoTreno;
	private int pesoMax;
	
	public PesoTrainatoException(String messaggio,String sigla, int pesoTreno, int pesoMax) {
		super(messaggio,sigla);
		this.pesoMax=pesoMax;
		this.pesoTreno=pesoTreno;
	}

	@Override
	public String toString() {
		return "Attenzione: il peso totale del treno ("+pesoTreno+" ton) supera il peso massimo che la locomotiva puo' muovere ("+pesoMax+" ton)!!!";
	}
	
}
