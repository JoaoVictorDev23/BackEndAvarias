package com.livecoding.estudos.controllers;

import com.livecoding.estudos.Errors.ErrorResponse;
import com.livecoding.estudos.domain.usuarios.DTO.CriarNotaFiscalDTO;
import com.livecoding.estudos.domain.usuarios.DTO.DadosNfdDTO;
import com.livecoding.estudos.services.ServicesInterface.DadosNFDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

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
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSituacao(@PathVariable String id, @RequestBody String situacao) {
        dadosNFDService.updateSituacao(id, situacao);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody DadosNfdDTO dadosNfdDTO) {
        dadosNFDService.updateDadosNfd(dadosNfdDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "Arquivo não foi enviado";
        }

        try {
            // Criar o diretório se ele não existir
            String uploadDir = "C://Users//joao.faria//Pictures//Teste/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Salvar o arquivo na pasta desejada no servidor
            String fileName = file.getOriginalFilename();
            String filePath = uploadDir + fileName;
            File dest = new File(filePath);
            file.transferTo(dest);

            return "Arquivo salvo com sucesso no servidor";
        } catch (IOException e) {
            return "Erro ao salvar o arquivo no servidor: " + e.getMessage();
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(String anexo) {
        try {
            // Diretório onde os arquivos estão armazenados
            String directoryPath = "C:/Users/joao.faria/Pictures/Teste/";

            // Caminho completo do arquivo
            String filePath = directoryPath + anexo ;

            // Verifica se o arquivo existe
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("Anexo não encontrado: " + anexo);
            }

            // Cria um recurso a partir do arquivo
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

            // Define os headers de resposta
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
