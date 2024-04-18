package com.livecoding.estudos.domain.usuarios.DTO;

import com.livecoding.estudos.domain.usuarios.Entidades.Pessoa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record PessoaDTO(
        String id,
        @NotBlank String nome,
        @NotNull String email,

        @NotNull String cpf,
        String cadastradoPor,
         Float debitado
) {
    public PessoaDTO(Pessoa pessoa) {
        this(pessoa.getPessoaId(), pessoa.getPessoaNome(),pessoa.getPessoaCpf(),pessoa.getPessoaEmail(),pessoa.getCadastradorPor(),pessoa.getPessoaDebitado());
    }
}
