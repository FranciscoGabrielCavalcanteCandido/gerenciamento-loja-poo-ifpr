package br.ifpr.poo;

import br.ifpr.poo.nucleo.servicos.Save;
import br.ifpr.poo.nucleo.servicos.ServicoCliente;
import br.ifpr.poo.nucleo.servicos.ServicoProduto;
import br.ifpr.poo.nucleo.servicos.ServicoVenda;

import java.util.Scanner;

public class App {
    ServicoCliente servicoCliente = new ServicoCliente();
    ServicoProduto servicoProduto = new ServicoProduto();
    Save save = new Save();

    public void menu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=-=-=-=-=-=-=-= M E N U - P R I N C I P A L =-=-=-=-=-=-=-=");
            System.out.println("1 - Acessar o Menu de Clientes" +
                    "\n2 - Acessar o Menu de Produtos" +
                    "\n3 - Sair");

            System.out.print("Escolha uma opção: ");
            Integer userInput = sc.nextInt();

            if (userInput == 1) {
                menuCliente();
            } else if (userInput == 2) {
                menuProduto();
            } else if (userInput == 3) {
                System.out.println("Fim do Programa! - Grupo AMDEV");
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    public void menuCliente() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=-=-=-=-=-=-=-= M E N U - C L I E N T E =-=-=-=-=-=-=-=");
            System.out.println("1 - Cadastrar Cliente" +
                    "\n2 - Remover o Cliente" +
                    "\n3 - Visualizar os Clientes cadastrados" +
                    "\n4 - Voltar ao Menu Principal");

            System.out.print("Escolha uma opção: ");
            Integer userInput = sc.nextInt();

            if (userInput == 1) {
                save.saveCliente();
            } else if (userInput == 2) {
                servicoCliente.excluir();
            } else if (userInput == 3) {
                servicoCliente.listarClientes();
            } else if (userInput == 4) {
                return;
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }

    public void menuProduto() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("=-=-=-=-=-=-=-= M E N U - P R O D U T O =-=-=-=-=-=-=-=");
            System.out.println("1 - Cadastrar Produto" +
                    "\n2 - Remover Produto" +
                    "\n3 - Visualizar todos os Produto" +
                    "\n4 - Voltar ao Menu Principal");

            System.out.print("Escolha uma opção: ");
            Integer userInput = sc.nextInt();

            if (userInput == 1) {
                save.saveProduto();
            } else if (userInput == 2) {
                servicoProduto.excluir();
            } else if (userInput == 3) {
                servicoProduto.listarProdutos();
            } else if (userInput == 4) {
                return;
            } else {
                System.out.println("Opção inválida!");
            }
        }
    }
}