package br.ifpr.poo.pedidos.dtos;

public class PedidoDTO {
	
	private Long id;
	private String nomePessoa;
	private Long quantidadeItens;
	
	
	public PedidoDTO(Long id, String nomePessoa, Long quantidadeItens) {
		this.id = id;
		this.nomePessoa = nomePessoa;
		this.quantidadeItens = quantidadeItens;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public Long getQuantidadeItens() {
		return quantidadeItens;
	}

	public void setQuantidadeItens(Long quantidadeItens) {
		this.quantidadeItens = quantidadeItens;
	}

	@Override
	public String toString() {
		return id + " - " + nomePessoa + " - itens: " + quantidadeItens;
	}
}
