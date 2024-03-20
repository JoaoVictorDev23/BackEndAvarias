package com.livecoding.estudos.domain.usuarios.DTO;

import com.livecoding.estudos.domain.usuarios.Entidades.DadosNFD;
import com.livecoding.estudos.domain.usuarios.Entidades.Motivo;
import com.livecoding.estudos.domain.usuarios.Entidades.Produtos;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosNfdDTO(
        Integer dadosnfdId,
        @NotNull String numeroNfd,
        @NotNull String numeroNfo,
        @NotNull String filial,
        @NotNull String serie,
        @NotNull  String cte,
         String observacao,
        String situacao,
        String cadastradoPor,
        String atualizadoPor,
        Motivo motivo

        ) {
    public DadosNfdDTO(DadosNFD dadosnfd){
        this(dadosnfd.getDadosnfdId(), dadosnfd.getNumeroNfd(), dadosnfd.getNumeroNfo(),
                dadosnfd.getFilial(),  dadosnfd.getSerie(), dadosnfd.getCte(), dadosnfd.getSituacao(),
                dadosnfd.getObservacao(),dadosnfd.getCadastradoPor(),dadosnfd.getAtualizadoPor(),dadosnfd.getMotivo());
    }
}
