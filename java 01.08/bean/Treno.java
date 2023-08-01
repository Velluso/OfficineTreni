package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Treno implements Serializable, Bean {

   private int idTreno;
   private String sigla;
   private String stato;
   private String nome;
   private List<Vagone> treno=new ArrayList<Vagone>();
   		
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
   
   public List<Vagone> getTreno() {
	   return treno;
   }
   public Treno() {}

   public Treno(String sigla, String stato) {
	   super();
	   this.sigla = sigla;
	   this.stato = stato;
   }

   public String getSigla() {
	   return sigla;
   }

   public void setSigla(String sigla) {
	   this.sigla = sigla;
   }

   public int getIdTreno() {
	   return idTreno;
   }

   public void setIdTreno(int idTreno) {
	   this.idTreno = idTreno;
   }

   public String getStato() {
	   return stato;
   }
   
   public void setStato( String stato ) {
	   this.stato = stato;
   }

   public String getNome() {
	return nome;
   }

   public void setNome(String nome) {
	this.nome = nome;
   }

   @Override
   public String toString() {
	   return "Treno [idTreno=" + idTreno + ", sigla=" + sigla + ", stato=" + stato + ", nome=" + nome + "]";
   }

   
   
}