package com.livecoding.estudos.controllers;

import com.livecoding.estudos.Errors.ErrorResponse;
import com.livecoding.estudos.domain.usuarios.DTO.ClienteDTO;
import com.livecoding.estudos.domain.usuarios.DTO.MotivosDto;
import com.livecoding.estudos.domain.usuarios.Entidades.Motivo;
import com.livecoding.estudos.domain.usuarios.repositories.MotivoRepository;
import com.livecoding.estudos.domain.usuarios.repositories.UsuarioRepository;
import com.livecoding.estudos.services.ServicesInterface.MotivoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/motivo")
public class MotivoControllers {


    @Autowired
    private MotivoService motivoService;


    @GetMapping("/buscar")
    public ResponseEntity<List<MotivosDto>> findAllMotivo(){
        List<MotivosDto> allMotivosDto = motivoService.findAllMotivo();
        return ResponseEntity.ok(allMotivosDto);

    }


    @PostMapping("/cadastrar")
    public ResponseEntity createMotivo(@RequestBody @Valid MotivosDto motivoDto) {
        try {
            motivoService.createMotivo(motivoDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity deleteMotivo(@PathVariable String id) {
        motivoService.deleteMotivo(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<MotivosDto> findById(@PathVariable String id) {
        MotivosDto MotivosDto = motivoService.findById(id);
        if (MotivosDto != null) {
            return ResponseEntity.ok(MotivosDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
