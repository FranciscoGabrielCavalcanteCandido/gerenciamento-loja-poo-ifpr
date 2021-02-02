package br.ifpr.poo.nucleo.servicos;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.ifpr.poo.infra.EntityManagerFactoryProducer;
import br.ifpr.poo.nucleo.entidades.Cidade;

public class CidadeServico {
	
	private final EntityManager entityManager;
	
	public CidadeServico(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public CidadeServico() {
		this.entityManager = EntityManagerFactoryProducer.createEntityManager();
	}
	
	public Cidade getCidadePorId(long id) {
		return getEntityManager().find(Cidade.class, id);
	}
	
	public List<Cidade> getTodasCidades() {
		return getEntityManager().createQuery("from Cidade", Cidade.class).getResultList();
	}
	
	public void inserir(Cidade cidade) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(cidade);
		getEntityManager().getTransaction().commit();
	}
	
	public void inserir(List<Cidade> cidades) {
		try {
			getEntityManager().getTransaction().begin();
			for (Cidade cidade : cidades) {
				getEntityManager().persist(cidade);
			}
			getEntityManager().getTransaction().commit();
		} catch (Exception ex) {
			getEntityManager().getTransaction().rollback();
		}
	}
	
	public Cidade atualizar(Cidade cidade) {
		getEntityManager().getTransaction().begin();
		Cidade managedCidade = getEntityManager().merge(cidade);
		getEntityManager().getTransaction().commit();
		return managedCidade;
	}
	
	public void excluir(long id) {
		Cidade cidade = getCidadePorId(id);
		if (cidade != null) {
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(cidade);
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

	public Optional<Cidade> getCidadePorNome(String nomeCidade) {
		TypedQuery<Cidade> query = getEntityManager().createQuery("SELECT c FROM Cidade c WHERE c.nome like :nomecidade", Cidade.class);
		List<Cidade> resultado = query.setParameter("nomecidade", nomeCidade.toUpperCase())
										.getResultList();
		if (resultado.size() > 0) {
			return Optional.of(resultado.get(0));
		}
		return Optional.empty();
	}
}
