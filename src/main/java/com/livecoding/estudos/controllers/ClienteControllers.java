package com.livecoding.estudos.controllers;

import com.livecoding.estudos.Errors.ErrorResponse;
import com.livecoding.estudos.domain.usuarios.DTO.ClienteDTO;
import com.livecoding.estudos.services.ServicesInterface.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
public class ClienteControllers {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable String id) {
        ClienteDTO clienteDTO = clienteService.findById(id);
        if (clienteDTO != null) {
            return ResponseEntity.ok(clienteDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDebitado(@PathVariable String id, @RequestBody Float debitado) {
        try {
            clienteService.updateDebitado(id, debitado);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("!ERRO! Ocorreu um erro ao atualizar o débito do cliente: " + e.getMessage());
        }
    }

}
