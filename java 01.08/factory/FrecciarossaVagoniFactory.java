package factory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import bean.*;

public class FrecciarossaVagoniFactory extends VagoniFactory {
	
	public FrecciarossaVagoniFactory() {
		super();
	}
	public Locomotiva costruisciLocomotiva() {
		return (FrecciarossaLocomotiva) context.getBean("FrecciarossaLocomotiva");
	}
	public Passeggeri costruisciPasseggeri() {
		return (FrecciarossaPasseggeri) context.getBean("FrecciarossaPasseggeri");
	}
	public Ristorante costruisciRistorante() {
		return (FrecciarossaRistorante) context.getBean("FrecciarossaRistorante");
	}
	public Cargo costruisciCargo() {
		return (FrecciarossaCargo) context.getBean("FrecciarossaCargo");
	}
	
}
