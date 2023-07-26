package service;

import java.util.List;

import bean.Treno;
import bean.Vagone;
import dao.TrenoDAOImpl;
import dao.TrenoDAO;
import builder.GenericBuilder;


public class TrenoService {

	private TrenoDAO dao = new TrenoDAOImpl();

    public void crea (Treno treno) {
    	Object id = dao.create(treno);
        System.out.println("creato il treno con id: " + id);
    }

    public void update (Treno treno) {
        dao.update(treno);
        System.out.println("modificato il treno con id: " + treno.getIdTreno());
    }

    public void cambiaStato (Treno treno) {

        treno.setStato("pronto");
        dao.update(treno);
        System.out.println("modificato il treno con id: " + treno.getIdTreno());
    }

    public Treno find(int id) {
    	return dao.find(id);
    }

	public TrenoService() {}
    
	public List<Vagone> cercaVagoniDiUnTreno(String idTreno){
		return dao.vagoniDiUnTreno(idTreno);
	}
	
	public String checkOrder(String stringaVagoni, String marchio) {
    	boolean check = true;
        String [] listaVagoni;
        int i;
        int locomotivaCounter = 0;
        int ristoranteCounter = 0;
        int cargoCounter = 0;
        int passeggeriCounter = 0;
        GenericBuilder trenoBuilder = new GenericBuilder();
        Treno treno = new Treno();
        String messaggio = "Richiesta di costruire treno " + marchio + ": " + stringaVagoni;
		
		System.out.println("Trying to build treno "+ marchio + " with string: " + stringaVagoni);

        listaVagoni = stringaVagoni.split("");
		
		/*try {
			
			switch (marchio.toLowerCase()) {
            case "frecciarossa":
                trenoBuilder = new TrenoBuilderFrecciaRossa();
                break;
            case "italo":
                trenoBuilder = new TrenoBuilderItalo();
                break;
            case "trenord":
                trenoBuilder = new TrenoBuilderTreNord();
                break;
            default:
            	trenoBuilder = new TrenoBuilderFrecciaRossa();
        }
			
			
            if (!listaVagoni[0].equalsIgnoreCase("h")) {
            	messaggio = "In testa al treno deve esserci una locomotiva";
            	check = false;
                throw new LocomotivaInTestaException("In testa al treno deve esserci una locomotiva", stringaVagoni, 'H');
            }

            for (i = 0; i < listaVagoni.length; i++) {
                switch (listaVagoni[i].toLowerCase()) {
                    case "h":
                        locomotivaCounter++;
                        break;
                    case "r":
                        ristoranteCounter++;
                        break;
                    case "c":
                        cargoCounter++;
                        break;
                    case "p":
                        passeggeriCounter++;
                        break;
                }
            }

            if (locomotivaCounter > 2) {
            	messaggio = "Numero massimo di locomotive raggiunto. Questa è una possibile configurazione: HPPPPH";
            	check = false;
                throw new MaxLocomotive("Numero massimo di locomotive raggiunto. " +
                        "Questa è una possibile configurazione: HPPPPH", stringaVagoni, 'H');
            }

            if (locomotivaCounter > 1) {
                if (!listaVagoni[listaVagoni.length -1].equalsIgnoreCase("H")) {
                	messaggio = "Le locomotoive devono essere in testa e in coda del treno. Questa è una possibile congifurazione: HCCCCCCH";
                	check = false;
                    throw new LocomotivaInTestaException("Le locomotoive devono essere in testa e in coda del treno. " +
                            "Questa è una possibile congifurazione: HCCCCCCH", stringaVagoni, 'H');
                }
            }

            if (ristoranteCounter > 1) {
            	messaggio = "Numero massimo di ristoranti raggiunto. Questa è una possibile configurazione: HPRPH";
            	check = false;
                throw new SingoloRistorante("Numero massimo di ristoranti raggiunto. " +
                        "Questa è una possibile configurazione: HPRPH", stringaVagoni, 'R');
            }

            if (cargoCounter > 0 && (passeggeriCounter > 0 || ristoranteCounter > 0)) {
            	messaggio = "Il treno non può essere composto sia da vagoni passeggeri o ristorante che cargo. " + 
            				"Questa è una possibile configurazione: HPRPH o HCCCCCH";
            	check = false;
                throw new TrenoCargo(
                        "Il treno non può essere composto sia da vagoni passeggeri o ristorante che cargo. " +
                                "Questa è una possibile configurazione: HPRPH o HCCCCCH", stringaVagoni, 'C');
            }

            if (ristoranteCounter > 0 && !listaVagoni[listaVagoni.length / 2].equals("R") && !listaVagoni[(listaVagoni.length / 2) + 1].equals("R")) {
                messaggio = "Il ristorante deve trovarsi nella posizione centrale del treno. " +
                        "Questa è una possibile configurazione: HPPRPH o HPPRPPH";
                check = false;
                throw new RistoranteCentrale("Il ristorante deve trovarsi nella posizione centrale del treno. " +
                        "Questa è una possibile configurazione: HPPRPH o HPPRPPH", stringaVagoni, 'R');
            }

            if (trenoBuilder.buildLocomotiva(0).getPeso() < (cargoCounter * trenoBuilder.buildCargo(0).getPeso() +
                    ristoranteCounter * trenoBuilder.buildRistorante(0).getPeso() + passeggeriCounter * trenoBuilder.buildPasseggeri(0).getPeso())) {
            	messaggio = "La forza della locomotiva non è sufficiente a trainare tutti i vagoni inseriti";
            	check = false;
                throw new PesoTrainante("La forza della locomotiva non è sufficiente a trainare tutti i vagoni inseriti", stringaVagoni, 'H');
            }

        } catch (LocomotivaInTestaException | MaxLocomotive | SingoloRistorante | TrenoCargo | RistoranteCentrale | PesoTrainante e) {
            e.printStackTrace();
        }
		*/
		if(check) {
			treno.setStato("in costruzione");
			treno.setSigla(stringaVagoni);
			crea(treno);
		}
		
		
		return messaggio;
    }

    

}

