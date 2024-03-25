package com.livecoding.estudos.services.servicesimpl;

import com.livecoding.estudos.domain.usuarios.DTO.CriarNotaFiscalDTO;
import com.livecoding.estudos.domain.usuarios.DTO.DadosNfdDTO;
import com.livecoding.estudos.domain.usuarios.DTO.ProdutosDTO;
import com.livecoding.estudos.domain.usuarios.DTO.ValoresNFDDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.DadosNFD;
import com.livecoding.estudos.domain.usuarios.Entidades.Produtos;
import com.livecoding.estudos.domain.usuarios.Entidades.ValoresNFD;
import com.livecoding.estudos.domain.usuarios.repositories.DadosNFDRepository;
import com.livecoding.estudos.domain.usuarios.repositories.ProdutosRepository;
import com.livecoding.estudos.domain.usuarios.repositories.ValoresNFDRepository;
import com.livecoding.estudos.services.ServicesInterface.DadosNFDService;
import com.livecoding.estudos.services.ServicesInterface.NotaFiscalService;
import com.livecoding.estudos.services.ServicesInterface.ProdutoService;
import com.livecoding.estudos.services.ServicesInterface.ValoresNfdService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotaFiscalServiceImpl implements NotaFiscalService {

    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private ValoresNfdService valoresNfdService;

    @Autowired
    private DadosNFDService dadosNFDService;


    @Override
    public void criarNotaFiscal(CriarNotaFiscalDTO criarNotaFiscalDTO) {
        // Criar os dados normais da Nota Fiscal (DadosNFD)
        DadosNfdDTO dadosNfdDTO = criarNotaFiscalDTO.dadosNfdDTO();
        dadosNFDService.createDados(dadosNfdDTO);

        //Criar os Dados de Valores

        ValoresNFDDTO valoresNFDDTO = criarNotaFiscalDTO.valoresDTO();
        valoresNfdService.createValoresNfd(valoresNFDDTO);


        // Criar os produtos associados à Nota Fiscal
        List<ProdutosDTO> produtosDTOList = criarNotaFiscalDTO.produtosDTO();
        produtoService.createProduto(produtosDTOList);
    }

    @Autowired
    private DadosNFDRepository dadosNFDRepository;

    @Autowired
    private ValoresNFDRepository valoresNFDRepository;

    @Autowired
    private ProdutosRepository produtosRepository;


    @Override
    public List<CriarNotaFiscalDTO> getAllNotasFiscais() {
        System.out.println("Iniciando getAllNotasFiscais...");

        List<DadosNFD> dadosNFDList = dadosNFDRepository.findAll();
        System.out.println("Total de dadosNFD encontrados: " + dadosNFDList.size());

        List<CriarNotaFiscalDTO> notaFiscalDTOList = new ArrayList<>();

        for (DadosNFD dadosNFD : dadosNFDList) {
            System.out.println("Processando dadosNFD: " + dadosNFD);

            ValoresNFD valoresNFD = valoresNFDRepository.findByNumeronfd(dadosNFD.getNumeroNfd());
            System.out.println("ValoresNFD encontrado: " + valoresNFD);

            if (valoresNFD != null) {
                List<Produtos> produtos = produtosRepository.findAllByNumeronfd(dadosNFD.getNumeroNfd());
                System.out.println("Total de produtos encontrados para numeroNfd " + dadosNFD.getNumeroNfd() + ": " + produtos.size());

                // Converter dadosNFD, valoresNFD e produtos para NotaFiscalDTO e adicionar a notaFiscalDTOList
                DadosNfdDTO dadosNfdDTO = new DadosNfdDTO(dadosNFD);
                List<ProdutosDTO> produtosDTOList = produtos.stream()
                        .map(ProdutosDTO::new)
                        .collect(Collectors.toList());
                ValoresNFDDTO valoresNFDDTO = new ValoresNFDDTO(valoresNFD);

                CriarNotaFiscalDTO notaFiscalDTO = new CriarNotaFiscalDTO(dadosNfdDTO, produtosDTOList, valoresNFDDTO);
                notaFiscalDTOList.add(notaFiscalDTO);
            } else {
                System.out.println("ValoresNFD não encontrado para numeroNfd: " + dadosNFD.getNumeroNfd());
            }
        }

        System.out.println("getAllNotasFiscais concluído. Total de CriarNotaFiscalDTO gerados: " + notaFiscalDTOList.size());

        return notaFiscalDTOList;
    }

    @Override
    public List<CriarNotaFiscalDTO> findAllByEmail() {
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        List<DadosNFD> dadosNFDList = dadosNFDRepository.findAllByCadastradoPor(emailUsuario);

        List<CriarNotaFiscalDTO> criarNotaFiscalDTOList = new ArrayList<>();

        for (DadosNFD dadosNFD : dadosNFDList) {
            DadosNfdDTO dadosNfdDTO = new DadosNfdDTO(dadosNFD); // Preencha com os dados necessários
            ValoresNFD valoresNfd = valoresNFDRepository.findByNumeronfd(dadosNFD.getNumeroNfd());
            ValoresNFDDTO valoresNFDDTO = new ValoresNFDDTO(valoresNfd);

            List<ProdutosDTO> produtosDTOList = new ArrayList<>();
            List<Produtos> produtosList = produtosRepository.findAllByNumeronfd(dadosNFD.getNumeroNfd());
            for (Produtos produto : produtosList) {
                ProdutosDTO produtosDTO = new ProdutosDTO(produto); // Preencha com os dados necessários
                produtosDTOList.add(produtosDTO);
            }

            CriarNotaFiscalDTO criarNotaFiscalDTO = new CriarNotaFiscalDTO(dadosNfdDTO, produtosDTOList, valoresNFDDTO);
            criarNotaFiscalDTOList.add(criarNotaFiscalDTO);
        }
        return criarNotaFiscalDTOList;
    }

    @Override
    public List<CriarNotaFiscalDTO> findAlls() {

        List<DadosNFD> dadosNFDList = dadosNFDRepository.findAll();

        List<CriarNotaFiscalDTO> criarNotaFiscalDTOList = new ArrayList<>();

        for (DadosNFD dadosNFD : dadosNFDList) {
            DadosNfdDTO dadosNfdDTO = new DadosNfdDTO(dadosNFD); // Preencha com os dados necessários
            ValoresNFD valoresNfd = valoresNFDRepository.findByNumeronfd(dadosNFD.getNumeroNfd());
            ValoresNFDDTO valoresNFDDTO = new ValoresNFDDTO(valoresNfd);

            List<ProdutosDTO> produtosDTOList = new ArrayList<>();
            List<Produtos> produtosList = produtosRepository.findAllByNumeronfd(dadosNFD.getNumeroNfd());
            for (Produtos produto : produtosList) {
                ProdutosDTO produtosDTO = new ProdutosDTO(produto); // Preencha com os dados necessários
                produtosDTOList.add(produtosDTO);
            }

            CriarNotaFiscalDTO criarNotaFiscalDTO = new CriarNotaFiscalDTO(dadosNfdDTO, produtosDTOList, valoresNFDDTO);
            criarNotaFiscalDTOList.add(criarNotaFiscalDTO);
        }
        return criarNotaFiscalDTOList;
    }


}
