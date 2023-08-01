package eccezioni;

import java.io.IOException;

public class LocomotivaException extends TrenoException {
	
	boolean flag;	//flag per distinguere le 2 eccezioni possibili: locomotiva in prima posizione OPPURE locomotiva in mezzo al treno
	
	public LocomotivaException(String messaggio, String sigla, boolean flag) {
		super(messaggio,sigla);
		this.flag=flag;
	}

	@Override
	public String toString() {
		if(flag) {
			return getMessage()+" Ti consiglio di aggiungere una locomotiva in prima posizione, come in questa sigla di esempio: h"+getSigla();
		}else {
			return getMessage();
		}
	}
	
	
	
}
