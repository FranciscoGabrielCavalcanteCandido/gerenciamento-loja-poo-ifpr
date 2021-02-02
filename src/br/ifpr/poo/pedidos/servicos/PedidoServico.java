package br.ifpr.poo.pedidos.servicos;

import java.util.List;

import javax.persistence.EntityManager;

import br.ifpr.poo.infra.EntityManagerFactoryProducer;
import br.ifpr.poo.pedidos.dtos.PedidoDTO;
import br.ifpr.poo.pedidos.entidades.Pedido;

public class PedidoServico {
	
private final EntityManager entityManager;
	
	public PedidoServico() {
		this.entityManager = EntityManagerFactoryProducer.createEntityManager();
	}
	
	public PedidoServico(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public Pedido getPedidoPorId(long id) {
		return getEntityManager().find(Pedido.class, id);
	}
	
	public List<Pedido> getTodosPedidosIneficiente() {
		return getEntityManager().createQuery("from Pedido", Pedido.class).getResultList();
	}
	
	public List<PedidoDTO> getTodosPedidos() {
		return getEntityManager().createQuery("SELECT new br.ifpr.poo.pedidos.dtos.PedidoDTO(pd.id, ps.nome, (SELECT COUNT(i) FROM ItemPedido i WHERE i MEMBER OF pd.itens))"
				+ "FROM Pedido pd INNER JOIN pd.pessoa ps", PedidoDTO.class).getResultList();
	}
	
	public void inserir(Pedido pedido) {
		getEntityManager().getTransaction().begin();
		getEntityManager().persist(pedido);
		getEntityManager().getTransaction().commit();
	}
	
	public Pedido atualizar(Pedido pedido) {
		getEntityManager().getTransaction().begin();
		Pedido managedPedido = getEntityManager().merge(pedido);
		getEntityManager().getTransaction().commit();
		return managedPedido;
	}
	
	public void excluir(long id) {
		Pedido pedido = getPedidoPorId(id);
		if (pedido != null) {
			getEntityManager().getTransaction().begin();
			getEntityManager().remove(pedido);
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
