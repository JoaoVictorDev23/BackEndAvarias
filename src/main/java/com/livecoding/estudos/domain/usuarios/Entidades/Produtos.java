package com.livecoding.estudos.domain.usuarios.Entidades;

import com.livecoding.estudos.domain.usuarios.DTO.ProdutosDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "produtos")
@Entity(name = "produtos")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String produtoId;

    @Column(name = "produto_nome")
    private String nomeProduto;

    @Column(name = "produto_valor")
    private Float valorProduto;

    @Column(name = "produto_quantidade")
    private Float quantidadeProduto;

    @Column(name = "produto_situacao")
    private String situacaoProduto;

    @Column(name = "produto_armazem")
    private Integer armazemId;

    @Column(name = "produto_nfd")
    private String numeronfd;

    public Produtos(@Valid ProdutosDTO produtosDTO){
        this.nomeProduto = produtosDTO.produtoNome();
        this.valorProduto = produtosDTO.valorProduto();
        this.quantidadeProduto = produtosDTO.quantidadeProduto();
        this.situacaoProduto = produtosDTO.situacaoProduto();
        this.numeronfd = produtosDTO.numeronfd();
        this.armazemId = produtosDTO.armazemId();

    }

}
