package com.livecoding.estudos.controllers;

import com.livecoding.estudos.Errors.ErrorResponse;
import com.livecoding.estudos.domain.usuarios.DTO.ClienteDTO;
import com.livecoding.estudos.services.ServicesInterface.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteControllers {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteDTO>> findAllClientes() {
        List<ClienteDTO> allClientesDto = clienteService.findAllCliente();
        return ResponseEntity.ok(allClientesDto);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> createCliente(@RequestBody @Valid ClienteDTO clienteDto) {
        try {
            clienteService.createCliente(clienteDto);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

}
