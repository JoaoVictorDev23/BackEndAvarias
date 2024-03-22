package com.livecoding.estudos.controllers;

import com.livecoding.estudos.Errors.ErrorResponse;
import com.livecoding.estudos.domain.usuarios.DTO.MotoristaDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.Motorista;
import com.livecoding.estudos.domain.usuarios.repositories.MotoristaRepository;
import com.livecoding.estudos.services.ServicesInterface.MotoristaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/motorista")
public class MotoristaController {

    @Autowired
    MotoristaService motoristaService;

    @PostMapping
    public ResponseEntity createMotorista(@RequestBody @Valid MotoristaDTO motoristaDTO){
        try {
            motoristaService.createMotorista(motoristaDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }


    @GetMapping("/listar")
    public ResponseEntity<List<MotoristaDTO>> findAllMotorista() {
        List<MotoristaDTO> allMotoristasDto = motoristaService.findAllMotorista();
        return ResponseEntity.ok(allMotoristasDto);
    }

    @GetMapping("listar/{id}")
    public ResponseEntity<MotoristaDTO> findByMotorista(@PathVariable String id) {
        MotoristaDTO motoristaDTO = motoristaService.findByMotorista(id);
        return ResponseEntity.ok(motoristaDTO);
    }

    @DeleteMapping("excluir/{id}")
    public ResponseEntity<MotoristaDTO> deleteMotorista(@PathVariable String id){
        try {
            motoristaService.deleteMotorista(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
