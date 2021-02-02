package br.ifpr.poo.nucleo.cadastros;

import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

import br.ifpr.poo.nucleo.entidades.Cidade;
import br.ifpr.poo.nucleo.entidades.Pessoa;
import br.ifpr.poo.nucleo.servicos.CidadeServico;
import br.ifpr.poo.nucleo.servicos.PessoaServico;

public class PessoaCadastro implements AutoCloseable {

	private final PessoaServico pessoaServico;
	private final CidadeServico cidadeServico;
	private final Scanner entrada;
	private final PrintStream saida;

	public PessoaCadastro() {
		pessoaServico = new PessoaServico();
		cidadeServico = new CidadeServico(pessoaServico.getEntityManager());
		entrada = new Scanner(System.in);
		saida = System.out;
	}

	public void executar() {
		String operacaoSelecionada = "";
		do {
			operacaoSelecionada = solicitarOpcao();
			executarOperacao(operacaoSelecionada);
		} while (!operacaoSelecionada.equals("0"));
	}

	private String solicitarOpcao() {
		saida.println("----- Cadastro de Pessoas -----");
		saida.println("1 - Listar Pessoas");
		saida.println("2 - Cadastrar Pessoa");
		saida.println("3 - Alterar Pessoa");
		saida.println("4 - Excluir Pessoa");
		saida.println("0 - Sair do Cadastro de Pessoas");
		// TODO outras operacoes, tais como buscar pelo nome, buscar pelo id, etc

		saida.println("Selecione uma opcao:");

		return entrada.nextLine();
	}

	private void executarOperacao(final String opcao) {
		if (opcao.equals("0")) {
			return;
		}
		
		switch (opcao) {
		case "1":
			listarPessoas();
			break;
		case "2":
			cadastrarPessoa();
			break;
		case "3":
			break;
		case "4":
			break;
		default:
			saida.println("Opcao Invalida!");
			break;
		}
		saida.println("Pressione [Enter] para continuar...");
		entrada.nextLine();
	}

	private void listarPessoas() {
		saida.println("---- Pessoas Cadastradas ----");
		for (Pessoa pessoa : pessoaServico.getTodasPessoas()) {
			saida.println(pessoa.getId() + " - " + pessoa.getNome());
		}
	}
	
	private void cadastrarPessoa() {
		saida.println("---- Cadastrar Pessoa ----");
		saida.println("Digite o nome:");
		String nome = entrada.nextLine();
		saida.println("Digite o nome da cidade: ");
		String nomeCidade = entrada.nextLine();
		Optional<Cidade> cidadeBuscada = cidadeServico.getCidadePorNome(nomeCidade);
		Cidade cidadeSelecionada;
		if (!cidadeBuscada.isPresent()) {
			saida.println("Cidade não encontrada, inserindo nova cidade...");
			cidadeSelecionada = new Cidade(nomeCidade.toUpperCase());
			cidadeServico.inserir(cidadeSelecionada);
		} else {
			cidadeSelecionada = cidadeBuscada.get();
		}
		
		Pessoa pessoaAInserir = new Pessoa(nome);
		pessoaAInserir.setCidade(cidadeSelecionada);
		pessoaServico.inserir(pessoaAInserir);
		saida.println("Pessoa inserida com sucesso!");
	}

	@Override
	public void close() throws Exception {
		pessoaServico.finalizar();
	}
}
