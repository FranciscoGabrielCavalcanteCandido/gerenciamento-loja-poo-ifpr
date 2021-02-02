package br.ifpr.poo;

import java.io.PrintStream;
import java.util.Scanner;

import br.ifpr.poo.infra.EntityManagerFactoryProducer;
import br.ifpr.poo.nucleo.cadastros.PessoaCadastro;

public class App {

	private final Scanner entrada;
	private final PrintStream saida;

	public App() {
		entrada = new Scanner(System.in);
		saida = System.out;
	}

	public void executar() {
		String operacaoSelecionada = "";
		do {
			operacaoSelecionada = solicitarOpcao();
			executarOperacao(operacaoSelecionada);
		} while (!operacaoSelecionada.equals("0"));
		EntityManagerFactoryProducer.closeDBConnection();
		saida.println("Sistema encerrado.");
	}

	private String solicitarOpcao() {
		saida.println("----- Sistema de Exemplo -----");
		saida.println("1 - Cadastro de Pessoas");
		saida.println("2 - Cadastro de Cidades");
		saida.println("3 - Gerenciamento de Pedidos");
		saida.println("0 - Sair do Sistema");

		saida.println("Selecione uma opcao:");

		return entrada.nextLine();
	}

	private void executarOperacao(final String opcao) {
		if (opcao.equals("0")) {
			return;
		}
		
		switch (opcao) {
		case "1":
			cadastroPessoa();
			break;

		case "2":

			break;
		// Implementar os demais cadastros

		default:
			saida.println("Opcao Invalida!");
			break;
		}

		saida.println("Pressione [Enter] para continuar...");
		entrada.nextLine();
	}

	private void cadastroPessoa() {
		try (PessoaCadastro pessoaCadastro = new PessoaCadastro()) {
			pessoaCadastro.executar();
		} catch (Exception e) {
			saida.println("Ocorreu um erro ao executar o cadastro de pessoas:");
			saida.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		new App().executar();
	}
}
