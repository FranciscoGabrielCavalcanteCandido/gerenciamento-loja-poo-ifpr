package br.ifpr.poo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.ifpr.poo.infra.EntityManagerFactoryProducer;
import br.ifpr.poo.infra.nucleo.entidades.Cidade;

public class App {

	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = EntityManagerFactoryProducer.getEntityManagerFactory();
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Cidade cidade1 = new Cidade("Paranavai");
		
		entityManager.getTransaction().begin();
		
		cidade1 = entityManager.merge(cidade1);
		
		entityManager.getTransaction().commit();
		
		Cidade cidadeBuscada = entityManager.find(Cidade.class, 1L);
		System.out.println(cidadeBuscada);
	}

}
