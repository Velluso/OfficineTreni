package eccezioni;

import java.io.IOException;

public class PosizioneRistoranteException extends TrenoException {
	
	public PosizioneRistoranteException(String messaggio,String sigla) {
		super(messaggio,sigla);
	}
}
