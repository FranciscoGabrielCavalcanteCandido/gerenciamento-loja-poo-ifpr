package br.ifpr.poo.nucleo.entidades;

import lombok.*;

import javax.persistence.*;

@Entity(name = "venda")
@Data
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_venda")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente idCliente;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto idProduto;
}