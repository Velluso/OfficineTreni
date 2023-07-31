package builder;

import bean.Treno;
import eccezioni.LocomotivaException;
import eccezioni.PesoTrainatoException;
import eccezioni.PosizioneRistoranteException;
import eccezioni.VagoneNonRiconosciutoException;
import eccezioni.VagoniIncompatibiliException;

public abstract class TrenoBuilder {
	
	public Treno costruisciTreno(String sigla) throws VagoneNonRiconosciutoException, VagoniIncompatibiliException, LocomotivaException, PosizioneRistoranteException, PesoTrainatoException {
		return null;
	}
	
}
