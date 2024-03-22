package com.livecoding.estudos.domain.usuarios.DTO;

import com.livecoding.estudos.domain.usuarios.Entidades.Motorista;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record MotoristaDTO(
        String id,
        @NotBlank String nome,
        @NotNull String email,

        @NotNull String cpf,
        String cadastradoPor,
        @NotNull Float debitado
) {
    public MotoristaDTO(Motorista motorista) {
        this(motorista.getMotoristaId(), motorista.getMotoristaNome(),motorista.getMotoristaCpf(),motorista.getMotoristaEmail(),motorista.getCadastradorPor(),motorista.getMotoristaDebitado());
    }
}
