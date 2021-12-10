package br.ifpr.poo.nucleo.servicos;

import br.ifpr.poo.nucleo.entidades.Cliente;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class ServicoCliente {
    private EntityManager entityManager;

    public ServicoCliente(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ServicoCliente() {
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Cliente> list() {
        return getEntityManager().createQuery("from Cliente", Cliente.class).getResultList();
    }

    public void listarClientes() {
        System.out.println("=-=-=-= Clientes Cadastrados =-=-=-=");
        for (Cliente cliente : list()) {
            System.out.println(cliente.getId() + " - " + cliente.getNome());
        }
    }

    public Cliente getById(Long id) {
        return getEntityManager().find(Cliente.class, id);
    }

    public void excluir() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Informe o ID do Cliente que você deseja excluir: ");
        Long id = sc.nextLong();

        Cliente cliente = getById(id);
        if (cliente != null) {
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(cliente);
            getEntityManager().getTransaction().commit();
        } else {
            System.out.println("Não há clientes cadastrados");
        }
    }
}