package com.livecoding.estudos.services.servicesimpl;

import com.livecoding.estudos.Errors.ErrorResponse;
import com.livecoding.estudos.domain.usuarios.DTO.PessoaDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.Pessoa;
import com.livecoding.estudos.domain.usuarios.repositories.PessoaRepository;
import com.livecoding.estudos.services.ServicesInterface.PessoaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Override
    public void createPessoa(PessoaDTO pessoaDTO) {
        Pessoa existingPessoa = this.pessoaRepository.findByPessoaCpf(pessoaDTO.cpf());
        if(existingPessoa != null){
            throw  new RuntimeException("Colaborador já existente!");

        }
        Pessoa newPessoa = new Pessoa(pessoaDTO);
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        newPessoa.setCadastradorPor(emailUsuario);

        pessoaRepository.save(newPessoa);
    }

    @Override
    public void deletePessoa(String id){
        pessoaRepository.deleteById(id);
    }

    @Override
    public PessoaDTO findByPessoa(String id){
        Optional<Pessoa> pessoaOptional = pessoaRepository.findById(id);
        Pessoa pessoa = pessoaOptional.orElseThrow(() -> new EntityNotFoundException("Colaborador não localizado"));
        return new PessoaDTO(pessoa);
    }

    @Override
    public List<PessoaDTO> findAllPessoa() {
        List<Pessoa> allPessoas = pessoaRepository.findAll();
        return allPessoas.stream()
                .map(PessoaDTO::new)
                .collect(Collectors.toList());
    }
}
