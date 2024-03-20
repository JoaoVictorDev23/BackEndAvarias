package com.livecoding.estudos.domain.usuarios.DTO;

import com.livecoding.estudos.domain.usuarios.Entidades.DadosNFD;
import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Not;

import java.util.List;

public record CriarNotaFiscalDTO(

        @NotNull DadosNfdDTO dadosNfdDTO,
        @NotNull List<ProdutosDTO> produtosDTO,

        @NotNull ValoresNFDDTO valoresDTO

) {


}