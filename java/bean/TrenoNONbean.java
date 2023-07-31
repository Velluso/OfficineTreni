package bean;

import java.util.*;

import eccezioni.*;

public class TrenoNONbean {
	
	private List<Vagone> treno=new ArrayList<Vagone>();
	
	public TrenoNONbean() {
		super();
	}
	
	public void aggiungiVagone(Vagone vagone) {
		treno.add(vagone);
	}
	
	public int peso() {
		int peso=0;
		for(int i=0;i<treno.size();i++) {
			peso+=treno.get(i).getPeso();
		}
		return peso;
	}

	@Override
	public String toString() {
		return "Treno [treno=" + treno.toString() + "]";
	}

	public List<Vagone> getTreno() {
		return treno;
	}
	
	
	/*
	public Treno(String stringa) throws LocomotivaException, VagoniIncompatibiliException {		//COSTRUTTORE COMPLESSO + ECCEZIONI
		String stringa2=stringa.toLowerCase();
		char[] caratteri=stringa2.toCharArray();	
		boolean presenzaCargo=false;
		boolean presenzaPasseggeri=false;
		boolean presenzaRistorante=false;
		
		for(int i=0;i<caratteri.length;i++) {
			
			if(i==0) {	//controllo locomotiva in testa
				if(caratteri[i]!='h')	throw new LocomotivaException("Il primo vagone deve essere una locomotiva!!!", stringa);
			}
			
			if(i!=0&&i!=caratteri.length-1){	//controllo locomotiva in mezzo
				if(caratteri[i]=='h')	throw new LocomotivaException("Non e' possibile inserire una locomotiva in mezzo al treno!!!", stringa);
			}
			
			if(caratteri[i]=='c')	presenzaCargo=true;			//controllo incompatibilità carrozze (C,P e R)
			if(caratteri[i]=='p')	presenzaPasseggeri=true;
			if(caratteri[i]=='r')	presenzaRistorante=true;
			
			if((presenzaCargo&&presenzaPasseggeri)||(presenzaCargo&&presenzaRistorante))	
				throw new VagoniIncompatibiliException("Vagoni di tipo cargo e di tipo passeggeri non sono compatibili sullo stesso treno!!!", stringa, 'r');
			
			
			switch(caratteri[i]) {	//composizione treno	+	//controllo tipologia vagone
				case 'h':	treno.add(new ItaloLocomotiva(200)); break;
				case 'p':	treno.add(new ItaloPasseggeri()); break;
				case 'r':	treno.add(new ItaloRistorante()); break;
				case 'c':	treno.add(new ItaloCargo(5));	break;
				default:	//throw new VagoneNonRiconosciutoException("Attenzione, puoi inserire solo 4 tipi di vagoni!!!");	
			}
		}
		
		
		
		//eccezione 'R non al centro'
		//eccezione 'più di un R'
		
	}
	*/
	/*
	public void aggiungiVagone(Vagone vagone, int posizione) {
		Collection<Vagone> partePrima=new ArrayList<Vagone>();
		Collection<Vagone> parteDopo=new ArrayList<Vagone>();
		for(int i=0;i<treno.size();i++) {
			if(i<posizione) {
				partePrima.add(treno.get(i));
			}
			if(i>=posizione) {
				parteDopo.add(treno.get(i));
			}
		}
		treno.clear();
		treno.addAll(partePrima);
		treno.add(vagone);
		treno.addAll(parteDopo);
	}
	
	public void rimuoviVagone(int posizione) {
		treno.remove(posizione-1);
	}
	
	public void contaVagoni(Vagone vagone) {
		
	}
	*/
	
}
