package com.livecoding.estudos.domain.usuarios.DTO;

import com.livecoding.estudos.domain.usuarios.Entidades.Produtos;
import jakarta.validation.constraints.NotNull;

public record ProdutosDTO(

        String produtosId,
        @NotNull String produtoNome,
        @NotNull Float valorProduto,
        @NotNull Float quantidadeProduto,
        @NotNull String situacaoProduto,

        @NotNull String numeronfd,

        Integer armazemId

) {

    public ProdutosDTO(Produtos produtos){
        this(produtos.getProdutoId(),produtos.getNomeProduto(), produtos.getValorProduto(), produtos.getQuantidadeProduto(),
                produtos.getSituacaoProduto(), produtos.getNumeronfd(), produtos.getArmazemId());
    }
}
