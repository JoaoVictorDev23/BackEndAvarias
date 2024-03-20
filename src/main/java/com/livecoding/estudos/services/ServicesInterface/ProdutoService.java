package com.livecoding.estudos.services.ServicesInterface;

import com.livecoding.estudos.domain.usuarios.DTO.ProdutosDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProdutoService {

    List<ProdutosDTO> createProduto(List<ProdutosDTO> produtosDTOList);
    List<ProdutosDTO> getAllProdutosForNFD(String numeroNfd);


}
