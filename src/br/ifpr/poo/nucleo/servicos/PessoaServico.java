package br.ifpr.poo.nucleo.servicos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.ifpr.poo.infra.EntityManagerFactoryProducer;
import br.ifpr.poo.nucleo.entidades.Pessoa;

public class PessoaServico {
	
	private final EntityManager entityManager;
	
	public PessoaServico() {
		this.entityManager = EntityManagerFactoryProducer.createEntityManager();
	}
	
	public PessoaServico(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Pessoa getPessoaPorId(long id) {
		return getEntityManager().find(Pessoa.class, id);
	}
	
	public List<Pessoa> getTodasPessoas() {
		return getEntityManager().createQuery("from Pessoa", Pessoa.class).getResultList();
	}
	
	public void inserir(Pessoa pessoa) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(pessoa);
		getEntityManager().getTransaction().commit();
	}
	
	public Pessoa atualizar(Pessoa pessoa) {
		getEntityManager().getTransaction().begin();
		Pessoa managedPessoa = getEntityManager().merge(pessoa);
		getEntityManager().getTransaction().commit();
		return managedPessoa;
	}
	
	public void excluir(long id) {
		Pessoa pessoa = getPessoaPorId(id);
		if (pessoa != null) {
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(pessoa);
			getEntityManager().getTransaction().commit();
		}
	}
	
	public void finalizar() {
		if (getEntityManager().isOpen()) {
			getEntityManager().close();
		}
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}
