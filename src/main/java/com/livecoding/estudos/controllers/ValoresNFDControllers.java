package com.livecoding.estudos.controllers;

import com.livecoding.estudos.domain.usuarios.DTO.ArmazemDTO;
import com.livecoding.estudos.domain.usuarios.DTO.CriarNotaFiscalDTO;
import com.livecoding.estudos.domain.usuarios.DTO.ValoresNFDDTO;
import com.livecoding.estudos.services.ServicesInterface.ValoresNfdService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("valoresnfd")
public class ValoresNFDControllers {

    @Autowired
    ValoresNfdService valoresNfdService;
    @PostMapping("/listar")
    public ResponseEntity<List<CriarNotaFiscalDTO>> getAllforArmazens(@RequestBody ArmazemDTO armazemdto){
        System.out.println(armazemdto);
        List<CriarNotaFiscalDTO> dadosArmazemDTO = valoresNfdService.findAll(armazemdto.armazemId());
        return ResponseEntity.ok(dadosArmazemDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSituacao(@PathVariable String id, @RequestBody String situacao) {
        valoresNfdService.updateSituacao(id, situacao);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateValores(@RequestBody ValoresNFDDTO valoresNFDDTO){
        valoresNfdService.updateValoresNfd(valoresNFDDTO);
        return ResponseEntity.ok().build();
    }

}
