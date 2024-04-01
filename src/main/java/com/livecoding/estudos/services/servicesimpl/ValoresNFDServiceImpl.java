package com.livecoding.estudos.services.servicesimpl;

import com.livecoding.estudos.domain.usuarios.DTO.CriarNotaFiscalDTO;
import com.livecoding.estudos.domain.usuarios.DTO.DadosNfdDTO;
import com.livecoding.estudos.domain.usuarios.DTO.ProdutosDTO;
import com.livecoding.estudos.domain.usuarios.DTO.ValoresNFDDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.Cliente;
import com.livecoding.estudos.domain.usuarios.Entidades.DadosNFD;
import com.livecoding.estudos.domain.usuarios.Entidades.Produtos;
import com.livecoding.estudos.domain.usuarios.Entidades.ValoresNFD;
import com.livecoding.estudos.domain.usuarios.repositories.*;
import com.livecoding.estudos.services.ServicesInterface.ValoresNfdService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ValoresNFDServiceImpl implements ValoresNfdService {

    @Autowired
    ValoresNFDRepository valoresNFDRepository;
    ClienteRepository clienteRepository;
    MotoristaRepository motoristaRepository;
    PessoaRepository pessoaRepository;


    @Override
    public ValoresNFDDTO findByValores(String id){
        Optional<ValoresNFD> optionalValoresNFD = valoresNFDRepository.findById(id);
        ValoresNFD valoresNFD = optionalValoresNFD.orElseThrow(()-> new EntityNotFoundException("Essa nota é inexistente."));
        return new ValoresNFDDTO(valoresNFD);

    }

    @Override
    public void createValoresNfd(ValoresNFDDTO valoresNFDDTO){

        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        ValoresNFD newValores = new ValoresNFD(valoresNFDDTO);
        newValores.setCadastradoPor(emailUsuario);
        newValores.setAtualizadoPor(emailUsuario);
        valoresNFDRepository.save(newValores);
    }

    @Override
    @Transactional
    public ValoresNFDDTO updateValoresNfd(ValoresNFDDTO valoresNFDDTO){
        Optional<ValoresNFD> optionalValoresNFD = valoresNFDRepository.findById(String.valueOf(valoresNFDDTO.id()));
        if(optionalValoresNFD.isPresent()){
            ValoresNFD valoresNFD = optionalValoresNFD.get();
            valoresNFD.setValorVenda(valoresNFDDTO.valorVenda());
            valoresNFD.setValorArmazem(valoresNFDDTO.valorArmazem());
            valoresNFD.setValorPrejuizo(valoresNFDDTO.valorPrejuizo());
            valoresNFD.setSituacaoValores("Pendente");
            valoresNFD.setArmazem(valoresNFDDTO.armazem());
            valoresNFD.setMotorista(valoresNFDDTO.motorista());
            valoresNFD.setComprador(valoresNFDDTO.comprador());
            valoresNFD.setCliente(valoresNFDDTO.cliente());
            valoresNFD.setDebitadoCliente(valoresNFDDTO.debitadoCliente());
            valoresNFD.setDebitadoMotorista(valoresNFDDTO.debitadoMotorista());

            //Log user que Atualizou:
            String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
            valoresNFD.setAtualizadoPor(emailUsuario);

            ValoresNFD updatedValoresNFD = valoresNFDRepository.save(valoresNFD);
            return new ValoresNFDDTO(updatedValoresNFD);

        }
        else{
            throw new RuntimeException("!ERRO! Entre em Contato com o Administrador!");

        }

    }

    @Autowired
    DadosNFDRepository dadosNFDRepository;

    @Override
    public List<CriarNotaFiscalDTO> findAll(Integer armazemId){
        List<ValoresNFD> valoresNFDS = valoresNFDRepository.findAllByArmazem(armazemId);

        List<CriarNotaFiscalDTO> criarNotasFiscaisDTO = new ArrayList<>();
        for (ValoresNFD valoresNFD : valoresNFDS) {

            DadosNFD dadosnfd = dadosNFDRepository.findByNumeroNfd(valoresNFD.getNumeronfd());

            DadosNfdDTO dadosNfdDTO = new DadosNfdDTO(dadosnfd);
            ValoresNFDDTO valoresNFDDTO = new ValoresNFDDTO(valoresNFD);

            CriarNotaFiscalDTO notaFiscalDTO = new CriarNotaFiscalDTO(dadosNfdDTO, new ArrayList<>(),valoresNFDDTO);
            criarNotasFiscaisDTO.add(notaFiscalDTO); // Adiciona o objeto à lista

        }

        return criarNotasFiscaisDTO;
    }
    @Override
    public void updateSituacao(String id, String situacao) {
        try {
            ValoresNFD valoresNFD = valoresNFDRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("ValoresNFD não encontrado com o ID: " + id));
            valoresNFD.setSituacaoValores(situacao);
            // Outros campos podem ser atualizados aqui, se necessário

            valoresNFDRepository.save(valoresNFD);
        } catch (RuntimeException e) {
            throw new RuntimeException("!ERRO! Ocorreu um erro ao atualizar a situação do DadosNFD: " + e.getMessage());
        }
    }
}
