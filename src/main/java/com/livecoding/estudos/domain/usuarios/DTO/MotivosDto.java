package com.livecoding.estudos.domain.usuarios.DTO;

import com.livecoding.estudos.domain.usuarios.Entidades.Motivo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Not;

public record MotivosDto(

        Integer  motivoId,
        @NotBlank String nomeMotivo,
        @NotNull String descriMotivo,
         String cadastradoPor

) {
    public MotivosDto(Motivo motivo) {
        this(motivo.getMotivoId(), motivo.getNomeMotivo(), motivo.getDescriMotivo(), motivo.getCadastradorPor());
    }


}