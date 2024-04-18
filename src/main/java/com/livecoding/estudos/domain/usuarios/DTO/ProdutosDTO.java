package com.livecoding.estudos.domain.usuarios.DTO;

import com.livecoding.estudos.domain.usuarios.Entidades.Produtos;
import jakarta.validation.constraints.NotNull;

public record ProdutosDTO(

        String produtosId,
        @NotNull String produtoNome,
        @NotNull Float produtoValor,
        @NotNull Float produtoQuantidade,
        @NotNull String situacaoProduto,

        @NotNull String numeronfd,

        Integer armazemId,
        Float produtoDesconto

) {

    public ProdutosDTO(Produtos produtos){
        this(produtos.getProdutoId(),produtos.getNomeProduto(), produtos.getValorProduto(), produtos.getQuantidadeProduto(),
                produtos.getSituacaoProduto(), produtos.getNumeronfd(), produtos.getArmazemId(), produtos.getProdutoDesconto());
    }
}
