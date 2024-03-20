package com.livecoding.estudos.services.servicesimpl;

import com.livecoding.estudos.domain.usuarios.DTO.ValoresNFDDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.ValoresNFD;
import com.livecoding.estudos.domain.usuarios.repositories.ValoresNFDRepository;
import com.livecoding.estudos.services.ServicesInterface.ValoresNfdService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ValoresNFDServiceImpl implements ValoresNfdService {

    @Autowired
    ValoresNFDRepository valoresNFDRepository;


    @Override
    public ValoresNFDDTO findByValores(String id){
        Optional<ValoresNFD> optionalValoresNFD = valoresNFDRepository.findById(id);
        ValoresNFD valoresNFD = optionalValoresNFD.orElseThrow(()-> new EntityNotFoundException("Essa nota é inexistente."));
        return new ValoresNFDDTO(valoresNFD);

    }

    @Override
    public void createValoresNfd(ValoresNFDDTO valoresNFDDTO){

        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        ValoresNFD newValores = new ValoresNFD(valoresNFDDTO);
        newValores.setCadastradoPor(emailUsuario);
        newValores.setAtualizadoPor(emailUsuario);
        valoresNFDRepository.save(newValores);
    }

    @Override
    @Transactional
    public ValoresNFDDTO updateValoresNfd(ValoresNFDDTO valoresNFDDTO){
        Optional<ValoresNFD> optionalValoresNFD = valoresNFDRepository.findById(String.valueOf(valoresNFDDTO.id()));
        if(optionalValoresNFD.isPresent()){
            ValoresNFD valoresNFD = optionalValoresNFD.get();
            valoresNFD.setValorVenda(valoresNFDDTO.valorVenda());
            valoresNFD.setValorArmazem(valoresNFDDTO.valorArmazem());
            valoresNFD.setValorPrejuizo(valoresNFDDTO.valorPrejuizo());
            valoresNFD.setSituacaoValores(valoresNFDDTO.situacaoValores());
            valoresNFD.setArmazem(valoresNFDDTO.armazem());
            valoresNFD.setMotorista(valoresNFDDTO.motorista());
            valoresNFD.setComprador(valoresNFDDTO.comprador());
            valoresNFD.setCliente(valoresNFDDTO.cliente());

            //Log user que Atualizou:
            String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
            valoresNFD.setAtualizadoPor(emailUsuario);

            ValoresNFD updatedValoresNFD = valoresNFDRepository.save(valoresNFD);
            return new ValoresNFDDTO(updatedValoresNFD);

        }
        else{
            throw new RuntimeException("!ERRO! Entre em Contato com o Administrador!");

        }

    }
}
