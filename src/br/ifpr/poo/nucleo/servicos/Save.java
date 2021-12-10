package br.ifpr.poo.nucleo.servicos;

import br.ifpr.poo.infra.EntityManagerFactoryProducer;
import br.ifpr.poo.nucleo.entidades.Cliente;
import br.ifpr.poo.nucleo.entidades.Produto;
import br.ifpr.poo.nucleo.entidades.Venda;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class Save {
    private final EntityManager entityManager;

    public Save() {
        this.entityManager = EntityManagerFactoryProducer.createEntityManager();
    }

    public Save(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void saveClient(Cliente cliente) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(cliente);
        getEntityManager().getTransaction().commit();
    }

    public void saveProduct(Produto produto) {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(produto);
        getEntityManager().getTransaction().commit();
    }

    public Cliente saveCliente() {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = new Cliente();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Endereço: ");
        String endereco = sc.nextLine();

        try {
            cliente = Cliente.builder()
                    .nome(nome)
                    .cpf(cpf)
                    .endereco(endereco)
                    .build();

            saveClient(cliente);
            System.out.println("Cliente cadastrado com sucesso");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente " + e.getMessage());
        }

        return cliente;
    }

    public Produto saveProduto() {
        Scanner sc = new Scanner(System.in);
        Produto produto = new Produto();

        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("Preço: ");
        Double preco = sc.nextDouble();

        System.out.print("Quantidade: ");
        Integer quantidade = sc.nextInt();

        try {
            produto = Produto.builder()
                    .nomeProduto(nome)
                    .preco(preco)
                    .quantidade(quantidade)
                    .build();

            saveProduct(produto);
            System.out.println("Produto cadastrado com sucesso");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar cliente " + e.getMessage());
        }

        return produto;
    }
}