package com.livecoding.estudos.domain.usuarios.DTO;

import com.livecoding.estudos.domain.usuarios.Entidades.Cliente;
import com.livecoding.estudos.domain.usuarios.Entidades.Pessoa;
import jakarta.persistence.Column;

public record ClienteDTO(
        Integer id,
        String nome,
        String cnpj,
        Float debitado,

       String cadastradorPor
) {

    public ClienteDTO(Cliente cliente) {
        this(cliente.getCliente_id(),cliente.getCliente_nome(), cliente.getClienteCnpj(),
                cliente.getDebitado(), cliente.getCadastradorPor());
    }
}
