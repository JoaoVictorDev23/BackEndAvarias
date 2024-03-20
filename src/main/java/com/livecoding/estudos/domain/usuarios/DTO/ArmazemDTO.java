package com.livecoding.estudos.domain.usuarios.DTO;

import com.livecoding.estudos.domain.usuarios.Entidades.Armazem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ArmazemDTO (

        Integer armazemId,
        @NotBlank String armazemNome,
        @NotNull String armazemEndereco,
        @NotNull String armazemFilial,
        String cadastradoPor
){
    public ArmazemDTO (Armazem armazem){
        this(armazem.getArmazemId(), armazem.getArmazemNome(), armazem.getArmazemEndereco(),armazem.getArmazemFilial(),armazem.getCadastradorPor());
    }
}
