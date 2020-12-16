package br.ifpr.poo.infra;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProducer {
	
	private static final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("exemploPU");
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return emFactory;
	}
	
	public static void closeDBConnection() {
		if (emFactory.isOpen())
			emFactory.close();
	}
}
