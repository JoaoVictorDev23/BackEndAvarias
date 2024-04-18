package com.livecoding.estudos.services.servicesimpl;

import com.livecoding.estudos.domain.usuarios.DTO.ProdutosDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.Produtos;
import com.livecoding.estudos.domain.usuarios.repositories.ProdutosRepository;
import com.livecoding.estudos.services.ServicesInterface.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    @Override
    public List<ProdutosDTO> atualizarOuAdicionarProdutos(List<ProdutosDTO> produtosDTOList) {
        List<ProdutosDTO> produtosAtualizados = new ArrayList<>();
        List<ProdutosDTO> novosProdutos = new ArrayList<>();

        for (ProdutosDTO produtosDTO : produtosDTOList) {
            String idDoProduto = produtosDTO.produtosId();

            // Verifica se o ID do produto é nulo e define como 0 se for
            if (idDoProduto == null) {
                idDoProduto = "0"; // Ou outro valor padrão adequado, se preferir
            }
            // Verifica se o produto já existe no banco de dados com base no ID
            Optional<Produtos> produtoExistenteOptional = produtosRepository.findById(idDoProduto);
            if (produtoExistenteOptional.isPresent()) {
                // Se o produto existir, atualiza os dados
                Produtos produtoExistente = produtoExistenteOptional.get();
                produtoExistente.setNomeProduto(produtosDTO.produtoNome());
                produtoExistente.setValorProduto(produtosDTO.produtoValor());
                produtoExistente.setQuantidadeProduto(produtosDTO.produtoQuantidade());
                produtoExistente.setProdutoDesconto(produtosDTO.produtoDesconto());
                produtoExistente.setSituacaoProduto(produtosDTO.situacaoProduto());
                produtoExistente.setArmazemId(produtosDTO.armazemId());
                produtosRepository.save(produtoExistente); // Atualiza o produto existente no banco de dados
                produtosAtualizados.add(produtosDTO); // Adiciona à lista de produtos atualizados
            } else {
                // Se o produto não existir, cria um novo
                Produtos novoProduto = new Produtos(produtosDTO);

                System.out.println(produtosDTO);
                produtosRepository.save(novoProduto); // Salva o novo produto no banco de dados
                novosProdutos.add(produtosDTO); // Adiciona à lista de novos produtos
            }
        }

        // Retorna a lista de produtos atualizados e novos produtos
        List<ProdutosDTO> result = new ArrayList<>(produtosAtualizados);
        result.addAll(novosProdutos);
        return result;
    }


}
