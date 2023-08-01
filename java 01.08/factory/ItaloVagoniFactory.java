package factory;

import javax.naming.Context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import bean.Cargo;
import bean.ItaloCargo;
import bean.ItaloLocomotiva;
import bean.ItaloPasseggeri;
import bean.ItaloRistorante;
import bean.Locomotiva;
import bean.Passeggeri;
import bean.Ristorante;

public class ItaloVagoniFactory extends VagoniFactory {

	public ItaloVagoniFactory() {
		super();
	}
	public Locomotiva costruisciLocomotiva() {
		return (ItaloLocomotiva) context.getBean("ItaloLocomotiva");
	}
	public Passeggeri costruisciPasseggeri() {
		return (ItaloPasseggeri) context.getBean("ItaloPasseggeri");
	}
	public Ristorante costruisciRistorante() {
		return (ItaloRistorante) context.getBean("ItaloRistorante");
	}
	public Cargo costruisciCargo() {
		return (ItaloCargo) context.getBean("ItaloCargo");
	}
	
}
