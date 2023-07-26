package eccezioni;

import java.io.IOException;

public class VagoneNonRiconosciutoException extends TrenoException {
	
	char intruso;
	
	public VagoneNonRiconosciutoException(String messaggio, String sigla, char intruso) {
		super(messaggio,sigla);
		this.intruso=intruso;
	}

	public char getIntruso() {
		return intruso;
	}
	
}
