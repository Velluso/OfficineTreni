package factory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.LazySingletonContext;

public abstract class VagoniFactory {
	
	ClassPathXmlApplicationContext context = LazySingletonContext.getInstance();
	
}
