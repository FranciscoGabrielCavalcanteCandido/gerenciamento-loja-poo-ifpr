package br.ifpr.poo.nucleo.servicos;

import br.ifpr.poo.nucleo.entidades.Venda;

import javax.persistence.EntityManager;
import java.util.List;

public class ServicoVenda {
    private EntityManager entityManager;

    public ServicoVenda(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ServicoVenda() {
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Venda> list() {
        if (getEntityManager() == null) {
            System.out.println("Nenhuma venda foi realizada");
        }
        return getEntityManager().createQuery("from Venda", Venda.class).getResultList();
    }

    public void listarVendas() {
        System.out.println("=-=-=-= Vendas Realizadas =-=-=-=");
        for (Venda venda : list()) {
            System.out.println("ID: " + venda.getId() +
                    "\nID Cliente: " + venda.getIdCliente() +
                    "\nID Produto: " + venda.getIdProduto());
        }
    }
}