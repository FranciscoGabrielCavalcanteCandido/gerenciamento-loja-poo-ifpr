package br.ifpr.poo;

import java.util.List;

import javax.persistence.EntityManager;

import br.ifpr.poo.infra.EntityManagerFactoryProducer;
import br.ifpr.poo.nucleo.entidades.Cidade;
import br.ifpr.poo.nucleo.entidades.Pessoa;
import br.ifpr.poo.nucleo.servicos.CidadeServico;
import br.ifpr.poo.nucleo.servicos.PessoaServico;
import br.ifpr.poo.pedidos.dtos.PedidoDTO;
import br.ifpr.poo.pedidos.entidades.ItemPedido;
import br.ifpr.poo.pedidos.entidades.Pedido;
import br.ifpr.poo.pedidos.entidades.Produto;
import br.ifpr.poo.pedidos.servicos.PedidoServico;

public class TestePedido {

	public static void main(String[] args) {
		
		EntityManager entityManager = EntityManagerFactoryProducer.createEntityManager();
		
		CidadeServico cidadeServico = new CidadeServico(entityManager);
		PessoaServico pessoaServico = new PessoaServico(entityManager);
		PedidoServico pedidoServico = new PedidoServico(entityManager);
		
		Cidade cidade1 = new Cidade("Paranavai");
		
		cidadeServico.inserir(cidade1);
		
		Pessoa pessoa1 = new Pessoa("Willian");
		pessoa1.setCidade(cidade1);
		
		Pessoa pessoa2 = new Pessoa("Maria");
		pessoa2.setCidade(cidade1);
		
		pessoaServico.inserir(pessoa1);
		pessoaServico.inserir(pessoa2);
		
		Produto produto1 = new Produto("Teclado");
		Produto produto2 = new Produto("Mouse");
		
		entityManager.getTransaction().begin();
		entityManager.persist(produto1);
		entityManager.persist(produto2);
		entityManager.getTransaction().commit();
		
		Pedido pedido1 = new Pedido(pessoa1);
		pedido1.addItem(new ItemPedido(produto1, 1));
		
		Pedido pedido2 = new Pedido(pessoa2);
		pedido2.addItem(new ItemPedido(produto2, 2));
		pedido2.addItem(new ItemPedido(produto1, 1));
		
		pedidoServico.inserir(pedido1);
		pedidoServico.inserir(pedido2);
		
		//O comando clear limpa o cache do entityManager. Dessa forma, a consulta deve ser feita direto no banco.
		entityManager.clear();
		
		List<PedidoDTO> pedidosdto = pedidoServico.getTodosPedidos();
		for (PedidoDTO pedidoDTO : pedidosdto) {
			System.out.println(pedidoDTO);
		}
		
//		entityManager.clear();
//		
//		List<Pedido> pedidos = pedidoServico.getTodosPedidosIneficiente();
//		for (Pedido pedido : pedidos) {
//			System.out.println(pedido.getId() + " - " + pedido.getPessoa().getNome() + " - " + pedido.getItems().size());
//		}
		
		entityManager.close();
		EntityManagerFactoryProducer.closeDBConnection();
	}
}
