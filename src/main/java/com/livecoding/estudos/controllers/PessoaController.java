package com.livecoding.estudos.controllers;

import com.livecoding.estudos.Errors.ErrorResponse;
import com.livecoding.estudos.domain.usuarios.DTO.PessoaDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.Pessoa;
import com.livecoding.estudos.domain.usuarios.repositories.PessoaRepository;
import com.livecoding.estudos.services.ServicesInterface.PessoaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @PostMapping
    public ResponseEntity createPessoa(@RequestBody @Valid PessoaDTO pessoaDTO){
        try {
            pessoaService.createPessoa(pessoaDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }


    @GetMapping("/listar")
    public ResponseEntity<List<PessoaDTO>> findAllPessoa() {
        List<PessoaDTO> allPessoasDto = pessoaService.findAllPessoa();
        return ResponseEntity.ok(allPessoasDto);
    }

    @GetMapping("listar/{id}")
    public ResponseEntity<PessoaDTO> findByPessoa(@PathVariable String id) {
        PessoaDTO pessoaDTO = pessoaService.findByPessoa(id);
        return ResponseEntity.ok(pessoaDTO);
    }

    @DeleteMapping("excluir/{id}")
    public ResponseEntity<PessoaDTO> deletePessoa(@PathVariable String id){
        try {
            pessoaService.deletePessoa(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
