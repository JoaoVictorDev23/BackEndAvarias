package com.livecoding.estudos.domain.usuarios.DTO;

import com.livecoding.estudos.domain.usuarios.Entidades.Armazem;
import com.livecoding.estudos.domain.usuarios.Entidades.Cliente;
import com.livecoding.estudos.domain.usuarios.Entidades.Pessoa;
import com.livecoding.estudos.domain.usuarios.Entidades.ValoresNFD;

import java.time.LocalDate;

public record ValoresNFDDTO(
        Integer id,
        Float valorVenda,
        Float valorPrejuizo,
        Float valorArmazem,
        String situacaoValores,

        Integer armazem,
        String motorista,
        String pessoa,

        Integer cliente,
        String cadastradoPor,
        String atualizadoPor,
        String numeronfd,
        Float debitadoCliente,
        Float debitadoMotorista,
        LocalDate data

) {
    public ValoresNFDDTO(ValoresNFD valoresNFD){
        this(valoresNFD.getValoresNFDID(), valoresNFD.getValorVenda(), valoresNFD.getValorPrejuizo(),
          valoresNFD.getValorArmazem(), valoresNFD.getSituacaoValores(),valoresNFD.getArmazem()
<<<<<<< HEAD
        , valoresNFD.getMotorista(),valoresNFD.getPessoa(),valoresNFD.getCliente()
=======
        , valoresNFD.getMotorista(),valoresNFD.getComprador(),valoresNFD.getCliente()
>>>>>>> ed9e4f358ffdf9f897197655fe01ad052bec2949
        , valoresNFD.getCadastradoPor(), valoresNFD.getAtualizadoPor(), valoresNFD.getNumeronfd(),
          valoresNFD.getDebitadoCliente(), valoresNFD.getDebitadoMotorista(),valoresNFD.getData());
    }

}
