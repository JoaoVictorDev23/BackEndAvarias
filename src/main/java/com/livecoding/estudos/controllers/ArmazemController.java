package com.livecoding.estudos.controllers;

import com.livecoding.estudos.Errors.ErrorResponse;
import com.livecoding.estudos.domain.usuarios.Entidades.Armazem;
import com.livecoding.estudos.domain.usuarios.DTO.ArmazemDTO;
import com.livecoding.estudos.domain.usuarios.repositories.ArmazemRepository;
import com.livecoding.estudos.services.ServicesInterface.ArmazemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/armazem")
public class ArmazemController {

    @Autowired
    ArmazemService armazemService;

    @GetMapping("/listar")
    public ResponseEntity<List<ArmazemDTO>> getAllArmazens(){
        List<ArmazemDTO> armazensDTO = armazemService.getAllArmazens();
        return ResponseEntity.ok(armazensDTO);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> createArmazens(@RequestBody @Valid ArmazemDTO armazemDTO) {

        try {
            armazemService.createArmazem(armazemDTO);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity deleteArmazem(@PathVariable String id){
        armazemService.deleteArmazem(id);
        return ResponseEntity.noContent().build();
    }
}
