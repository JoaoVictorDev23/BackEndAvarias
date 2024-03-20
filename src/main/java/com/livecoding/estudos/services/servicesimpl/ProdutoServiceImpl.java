package com.livecoding.estudos.services.servicesimpl;

import com.livecoding.estudos.domain.usuarios.DTO.ProdutosDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.Produtos;
import com.livecoding.estudos.domain.usuarios.repositories.ProdutosRepository;
import com.livecoding.estudos.services.ServicesInterface.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    ProdutosRepository produtosRepository;

    @Override
    public List<ProdutosDTO> createProduto(List<ProdutosDTO> produtosDTOList) {
        List<Produtos> produtosList = produtosDTOList.stream()
                .map(Produtos::new)
                .collect(Collectors.toList());


        List<Produtos> savedProdutos = produtosRepository.saveAll(produtosList);

        return savedProdutos.stream()
                .map(ProdutosDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProdutosDTO> getAllProdutosForNFD(String numeroNfd) {
        List<Produtos> allProdutosForNFD = produtosRepository.findAllByNumeronfd(numeroNfd);
        return allProdutosForNFD.stream()
                .map(ProdutosDTO::new)
                .collect(Collectors.toList());
    }



}
