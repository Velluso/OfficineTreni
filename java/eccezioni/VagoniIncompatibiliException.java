package eccezioni;

import java.io.IOException;

public class VagoniIncompatibiliException extends TrenoException {

	private char vagoneIncompatibile;

	public VagoniIncompatibiliException(String messaggio, String sigla, char vagoneIncompatibile) {
		super(messaggio,sigla);
		this.vagoneIncompatibile=vagoneIncompatibile;
	}

	public char getVagoneIncompatibile() {
		return vagoneIncompatibile;
	}
	
	
	
	
}
