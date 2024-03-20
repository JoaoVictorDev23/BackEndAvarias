package com.livecoding.estudos.controllers;


import com.livecoding.estudos.domain.usuarios.DTO.ArmazemDTO;
import com.livecoding.estudos.domain.usuarios.DTO.ProdutosDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.Produtos;
import com.livecoding.estudos.domain.usuarios.repositories.ProdutosRepository;
import com.livecoding.estudos.services.ServicesInterface.ProdutoService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produtos")
public class ProdutosControllers {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/buscarProduto")
    public ResponseEntity<List<ProdutosDTO>> getAllProdutosForNFD(@RequestParam(name = "numeroNfd") String numeroNfd) {
        List<ProdutosDTO> produtos = produtoService.getAllProdutosForNFD(numeroNfd);
        return ResponseEntity.ok(produtos);
    }


    @PostMapping("/cadastrar")
    public ResponseEntity<List<ProdutosDTO>> cadastrarProdutos(@RequestBody List<ProdutosDTO> produtosDTOList) {
        List<ProdutosDTO> savedProdutos = produtoService.createProduto(produtosDTOList);

        return ResponseEntity.ok(savedProdutos);
    }
}
