package br.ifpr.poo.nucleo.servicos;

import br.ifpr.poo.nucleo.entidades.Cliente;
import br.ifpr.poo.nucleo.entidades.Produto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class ServicoProduto {
    private EntityManager entityManager;

    public ServicoProduto(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public ServicoProduto() {
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Produto> list() {
        return getEntityManager().createQuery("from Produto", Produto.class).getResultList();
    }

    public void listarProdutos() {
        System.out.println("=-=-=-= Produtos Cadastrados =-=-=-=");
        for (Produto produto : list()) {
            System.out.println(produto.getId() + " - " + produto.getNomeProduto());
        }
    }

    public Produto getById(Long id) {
        return getEntityManager().find(Produto.class, id);
    }

    public void excluir() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Informe o ID do Produto que você deseja excluir: ");
        Long id = sc.nextLong();

        Produto produto = getById(id);
        if (produto != null) {
            getEntityManager().getTransaction().begin();
            getEntityManager().remove(produto);
            getEntityManager().getTransaction().commit();
        }
    }
}