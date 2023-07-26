package factory;

import javax.naming.Context;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import bean.*;

public class TreNordVagoniFactory extends VagoniFactory {
	
	public TreNordVagoniFactory() {
		super();
	}
	public Locomotiva costruisciLocomotiva() {
		return (TreNordLocomotiva) context.getBean("TreNordLocomotiva");
	}
	public Passeggeri costruisciPasseggeri() {
		return (TreNordPasseggeri) context.getBean("TreNordPasseggeri");
	}
	public Ristorante costruisciRistorante() {
		return (TreNordRistorante) context.getBean("TreNordRistorante");
	}
	public Cargo costruisciCargo() {
		return (TreNordCargo) context.getBean("TreNordCargo");
	}
	
}
