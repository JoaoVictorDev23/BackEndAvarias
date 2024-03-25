package com.livecoding.estudos.services.ServicesInterface;

import com.livecoding.estudos.domain.usuarios.DTO.PessoaDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.Pessoa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PessoaService {


    void createPessoa(PessoaDTO pessoaDTO);
    List<PessoaDTO> findAllPessoa();

    PessoaDTO findByPessoa(String id);

    void deletePessoa(String id);


}
