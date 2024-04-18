package com.livecoding.estudos.controllers;

import com.livecoding.estudos.Errors.ErrorResponse;
import com.livecoding.estudos.domain.usuarios.DTO.CriarNotaFiscalDTO;
import com.livecoding.estudos.domain.usuarios.DTO.DadosNfdDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.Arquivos;
import com.livecoding.estudos.domain.usuarios.repositories.ArquivoRepository;
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

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    //Parte de Upload e Dowload de Arquivos:

    @Autowired
    private ArquivoRepository arquivosRepository;
    @PostMapping("/upload")
    public ResponseEntity<?>  uploadFiles(@RequestParam("files") List<MultipartFile> files, @RequestParam("numeroNfd") String numeroNfd) {
        if (files.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhum arquivo foi enviado");
        }

        try {
            // Diretório onde os arquivos serão salvos
            String uploadDir = "G:/Meu Drive/Sistema avarias/NotaDevolucoes/";
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Iterar sobre cada arquivo na lista e salvá-los no servidor
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String fileName = file.getOriginalFilename();
                    String filePath = uploadDir + fileName;
                    File dest = new File(filePath);
                    file.transferTo(dest);

                    // Salvar os dados do arquivo no banco de dados
                    Arquivos arquivo = new Arquivos();
                    arquivo.setNomeArquivo(fileName);
                    arquivo.setNumeronfd(numeroNfd);
                    arquivosRepository.save(arquivo);
                }
            }

            return ResponseEntity.ok("Arquivos salvos com sucesso no servidor e no banco de dados");
        } catch (IOException e) {
            System.out.println("erro");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar os arquivos no servidor: " + e.getMessage());
        }
    }


    @GetMapping("/download/{numeroNfd}")
    public ResponseEntity<Object> downloadFiles(@PathVariable String numeroNfd) {
        // Diretório onde os arquivos estão armazenados
        String uploadDir = "G:/Meu Drive/Sistema avarias/NotaDevolucoes/";

        // Consultar o banco de dados para obter todos os arquivos relacionados à nota fiscal
        List<Arquivos> arquivosNotaFiscal = arquivosRepository.findAllByNumeronfd(numeroNfd);

        if (arquivosNotaFiscal.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum arquivo encontrado para a nota fiscal: " + numeroNfd);
        }

        try {
            // Criar um arquivo zip temporário para armazenar todos os arquivos
            File zipFile = File.createTempFile("arquivos", ".zip");
            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zipOut = new ZipOutputStream(fos);

            // Adicionar cada arquivo relacionado à nota fiscal ao arquivo zip
            for (Arquivos arquivo : arquivosNotaFiscal) {
                String fileName = arquivo.getNomeArquivo();
                String filePath = uploadDir + fileName;
                File file = new File(filePath);
                if (file.exists()) {
                    FileInputStream fis = new FileInputStream(file);
                    ZipEntry zipEntry = new ZipEntry(fileName);
                    zipOut.putNextEntry(zipEntry);
                    byte[] bytes = new byte[1024];
                    int length;
                    while ((length = fis.read(bytes)) >= 0) {
                        zipOut.write(bytes, 0, length);
                    }
                    fis.close();
                }
            }

            // Fechar o arquivo zip e os streams
            zipOut.close();
            fos.close();

            // Cria um recurso a partir do arquivo zip
            InputStreamResource resource = new InputStreamResource(new FileInputStream(zipFile));

            // Define os headers de resposta
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=arquivos.zip");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(zipFile.length())
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o arquivo zip: " + e.getMessage());
        }
    }


}
