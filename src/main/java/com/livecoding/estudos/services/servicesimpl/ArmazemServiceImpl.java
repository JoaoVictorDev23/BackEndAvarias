package com.livecoding.estudos.services.servicesimpl;

import com.livecoding.estudos.Errors.ErrorResponse;
import com.livecoding.estudos.domain.usuarios.DTO.ArmazemDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.Armazem;
import com.livecoding.estudos.domain.usuarios.repositories.ArmazemRepository;
import com.livecoding.estudos.services.ServicesInterface.ArmazemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArmazemServiceImpl implements ArmazemService {


    @Autowired
    ArmazemRepository armazemRepository;


    @Override
    public void createArmazem(ArmazemDTO armazemDTO){
        Armazem existingArmazem = this.armazemRepository.findByArmazemNome(armazemDTO.armazemNome());
        if (existingArmazem != null) {

            throw new RuntimeException("Armazém já existente!");
        }

        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        Armazem newArmazem = new Armazem(armazemDTO);
        newArmazem.setCadastradorPor(emailUsuario);
        armazemRepository.save(newArmazem);
    }

    @Override
    public void deleteArmazem(String id){
        armazemRepository.deleteById(id);
    }

    @Override
    public List<ArmazemDTO> getAllArmazens(){
        List<Armazem> allArmazens = armazemRepository.findAll();
        return allArmazens.stream()
                .map(ArmazemDTO::new)
                .collect(Collectors.toList());
    }


}
