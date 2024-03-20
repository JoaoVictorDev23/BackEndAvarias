package com.livecoding.estudos.controllers;

import com.livecoding.estudos.Errors.ErrorResponse;
import com.livecoding.estudos.domain.usuarios.DTO.DadosNfdDTO;
import com.livecoding.estudos.services.ServicesInterface.DadosNFDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nfd/dados")
public class DadosNFDControllers {

    @Autowired
    DadosNFDService dadosNFDService;


    @PostMapping("/cadastrar")
    public ResponseEntity createDados(@RequestBody DadosNfdDTO dadosNfdDTO){
        try {
            dadosNFDService.createDados(dadosNfdDTO);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }

    }
}
