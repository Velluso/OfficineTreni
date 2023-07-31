package builder;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import bean.Treno;
import eccezioni.*;
import factory.*;
import service.LazySingletonContext;

public class GenericBuilder extends TrenoBuilder {

	public Treno costruisciTreno(String sigla, String compagnia, String nome) throws VagoneNonRiconosciutoException,VagoniIncompatibiliException,LocomotivaException, PosizioneRistoranteException,PesoTrainatoException {
		
		ClassPathXmlApplicationContext context = LazySingletonContext.getInstance();
		Treno treno=(Treno) context.getBean("Treno");
		treno.setSigla(sigla);
		treno.setNome(nome);
		treno.setStato("in costruzione");
		char[] caratteri=sigla.toLowerCase().toCharArray();
		ItaloVagoniFactory ivf = (ItaloVagoniFactory) context.getBean("ItaloVagoniFactory");
		FrecciarossaVagoniFactory fvf = (FrecciarossaVagoniFactory) context.getBean("FrecciarossaVagoniFactory");
		TreNordVagoniFactory tnvf = (TreNordVagoniFactory) context.getBean("TreNordVagoniFactory");
		boolean presenzaCargo=false;
		boolean presenzaPasseggeri=false;
		boolean presenzaRistorante=false;
		int numRistoranti=-1;
		int pesoTrainato=-1;
		int centroTreno=-1;
		boolean pari=false;
		if(sigla.length()%2==0) {
			centroTreno=sigla.length()/2;
			pari=true;
		}else {
			centroTreno=Math.round((sigla.length()/2));	//arrotondato per difetto
			pari=false;
		}
				
		for(int i=0;i<caratteri.length;i++) {
			//controllo locomotiva in testa
			if(i==0) {	
				if(caratteri[i]!='h')	throw new LocomotivaException("Il primo vagone deve essere una locomotiva!!!", sigla, true);
			}
			//controllo locomotiva in mezzo
			if(i!=0&&i!=caratteri.length-1){	
				if(caratteri[i]=='h')	throw new LocomotivaException("Non e' possibile inserire una locomotiva in mezzo al treno!!!", sigla, false);
			}
			//controllo incompatibilità vagoni (C,P e R)
			if(caratteri[i]=='c')	presenzaCargo=true;			
			if(caratteri[i]=='p')	presenzaPasseggeri=true;
			if(caratteri[i]=='r')	presenzaRistorante=true;
			if((presenzaCargo&&presenzaPasseggeri)||(presenzaCargo&&presenzaRistorante))	
				throw new VagoniIncompatibiliException("Vagoni di tipo cargo e di tipo passeggeri non sono compatibili sullo stesso treno!!!", sigla, 'r');
			//controllo numero ristoranti
			if(numRistoranti>1)	throw new PosizioneRistoranteException("Non è possibile costruire un treno con più di 1 ristorante!!!", sigla);
			//controllo ristorante non al centro
			if(pari&&i!=centroTreno-1&&i!=centroTreno){
				if(caratteri[i]=='r')	throw new PosizioneRistoranteException("Attenzione: il vagone ristorante deve essere sistemato al centro del treno!!!",sigla);
			}
			if(!pari&&i!=centroTreno)	
				if(caratteri[i]=='r')	throw new PosizioneRistoranteException("Attenzione: il vagone ristorante deve essere sistemato al centro del treno!!!",sigla);
			//aggiunta vagone al treno	+	controllo tipologia vagone
			if(compagnia.compareTo("Italo")==0) {
				switch(caratteri[i]) {
					case 'h':	
						treno.aggiungiVagone(ivf.costruisciLocomotiva());
						if(i==0)	pesoTrainato=2000;
						break;
					case 'p':	
						treno.aggiungiVagone(ivf.costruisciPasseggeri()); 
						break;
					case 'r':	
						treno.aggiungiVagone(ivf.costruisciRistorante()); 
						numRistoranti++; 
						break;
					case 'c':	
						treno.aggiungiVagone(ivf.costruisciCargo());	
						break;
					default:	
						throw new VagoneNonRiconosciutoException("Attenzione, puoi inserire solo 4 tipi di vagoni!!!",sigla,caratteri[i]);	
				}
			}else if(compagnia.compareTo("Frecciarossa")==0) {
				switch(caratteri[i]) {
					case 'h':	
						treno.aggiungiVagone(fvf.costruisciLocomotiva());
						if(i==0)	pesoTrainato=2000;
						break;
					case 'p':	
						treno.aggiungiVagone(fvf.costruisciPasseggeri()); 
						break;
					case 'r':	
						treno.aggiungiVagone(fvf.costruisciRistorante()); 
						numRistoranti++; 
						break;
					case 'c':	
						treno.aggiungiVagone(fvf.costruisciCargo());	
						break;
					default:	
						throw new VagoneNonRiconosciutoException("Attenzione, puoi inserire solo 4 tipi di vagoni!!!",sigla,caratteri[i]);	
				}
			}else if(compagnia.compareTo("TreNord")==0) {
				switch(caratteri[i]) {
					case 'h':	
						treno.aggiungiVagone(tnvf.costruisciLocomotiva());
						if(i==0)	pesoTrainato=2000;
						break;
					case 'p':	
						treno.aggiungiVagone(tnvf.costruisciPasseggeri()); 
						break;
					case 'r':	
						treno.aggiungiVagone(tnvf.costruisciRistorante()); 
						numRistoranti++; 
						break;
					case 'c':	
						treno.aggiungiVagone(tnvf.costruisciCargo());	
						break;
					default:	
						throw new VagoneNonRiconosciutoException("Attenzione, puoi inserire solo 4 tipi di vagoni!!!",sigla,caratteri[i]);	
				}
			}
		}
		//controllo peso trainato
		if(pesoTrainato<treno.peso()) {
			throw new PesoTrainatoException("",sigla,treno.peso(),pesoTrainato);
		}
		return treno;
	}
}
