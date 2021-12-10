package br.ifpr.poo.nucleo.entidades;

import lombok.*;

import javax.persistence.*;

@Entity(name = "produto")
@Data
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_produto")
    private Long id;

    @Column(name = "nome")
    private String nomeProduto;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "quantidade")
    private Integer quantidade;
}