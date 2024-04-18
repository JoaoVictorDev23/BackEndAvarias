package com.livecoding.estudos.controllers;

import com.livecoding.estudos.Errors.ErrorResponse;
import com.livecoding.estudos.domain.usuarios.DTO.CriarNotaFiscalDTO;
import com.livecoding.estudos.services.ServicesInterface.NotaFiscalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nota")
public class NotaFiscalControllers {
    @Autowired
    private NotaFiscalService notaFiscalService;

    @PostMapping("/criar")
    public ResponseEntity criarNotaFiscal(@Valid @RequestBody CriarNotaFiscalDTO criarNotaFiscalDTO) {
        try {
            notaFiscalService.criarNotaFiscal(criarNotaFiscalDTO);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }
    @GetMapping("/buscar")
    public ResponseEntity<List<CriarNotaFiscalDTO>> getAllNotasFiscais() {
        List<CriarNotaFiscalDTO> notasFiscais = notaFiscalService.getAllNotasFiscais();
        return ResponseEntity.ok(notasFiscais);
    }
    @GetMapping("/listar")
    public ResponseEntity<List<CriarNotaFiscalDTO>> listarNotasFiscais() {
        List<CriarNotaFiscalDTO> notasFiscais = notaFiscalService.findAllByEmail(); // Supondo que o método findAll retorne a lista de CriarNotaFiscalDTO

        if (notasFiscais.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(notasFiscais);
        }
    }
    @GetMapping("/listar/todas")
    public ResponseEntity<List<CriarNotaFiscalDTO>> todaslistarNotasFiscais() {
        List<CriarNotaFiscalDTO> notasFiscais = notaFiscalService.findAlls(); // Supondo que o método findAll retorne a lista de CriarNotaFiscalDTO

        if (notasFiscais.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(notasFiscais);
        }
    }
    @PostMapping("/gerar-excel")
    public ResponseEntity<byte[]> gerarExcel(@RequestParam("nomeArquivo") String nomeArquivo) {
        try {
            List<CriarNotaFiscalDTO> notas = notaFiscalService.findAlls();
            byte[] excelBytes = notaFiscalService.criarExcel(nomeArquivo, notas);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", nomeArquivo + ".xlsx");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}