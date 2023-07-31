package service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LazySingletonContext {
	
	private static ClassPathXmlApplicationContext context = null; 


	private LazySingletonContext() {
		System.out.println("costruttore di LazySingleton...");		
	}
	
	public static ClassPathXmlApplicationContext getInstance() {
		if (context==null) {
			context = new ClassPathXmlApplicationContext("springBeansTreno.xml");
		}		
		return context; 
	}

	
}
