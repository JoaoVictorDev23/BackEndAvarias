package com.livecoding.estudos.services.servicesimpl;

import com.livecoding.estudos.domain.usuarios.DTO.DadosNfdDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.DadosNFD;
import com.livecoding.estudos.domain.usuarios.repositories.DadosNFDRepository;
import com.livecoding.estudos.services.ServicesInterface.DadosNFDService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class DadosNFDServiceImpl implements DadosNFDService {

    @Autowired
    DadosNFDRepository dadosNFDRepository;

    @Override
    public void createDados(DadosNfdDTO dadosNfdDTO){
        DadosNFD dadosexisting = this.dadosNFDRepository.findByNumeroNfd(dadosNfdDTO.numeroNfd());
        if(dadosexisting != null){
            throw new RuntimeException("Nota Fiscal de Devolução já cadastrada!");
        }
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
        LocalDate dataLocal = LocalDate.now(); // Obtém a data local atual


        DadosNFD newDados = new DadosNFD(dadosNfdDTO);
        newDados.setData(dataLocal);
        newDados.setAtualizadoPor(emailUsuario);
        newDados.setCadastradoPor(emailUsuario);
        dadosNFDRepository.save(newDados);
    }

    @Override
    @Transactional
    public DadosNfdDTO updateDadosNfd(DadosNfdDTO dadosNfdDTO) {
        Optional<DadosNFD> optionalDadosNFD = dadosNFDRepository.findById(String.valueOf(dadosNfdDTO.dadosnfdId()));
        if (optionalDadosNFD.isPresent()) {
            DadosNFD dadosNFD = optionalDadosNFD.get();

            dadosNFD.setMotivo(dadosNfdDTO.motivo());
            dadosNFD.setSituacao("Pendente");


            // Log user que Atualizou:
            String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
            dadosNFD.setAtualizadoPor(emailUsuario);

            DadosNFD updatedDadosNFD = dadosNFDRepository.save(dadosNFD);
            return new DadosNfdDTO(updatedDadosNFD);
        } else {
            throw new RuntimeException("!ERRO! Entre em Contato com o Administrador!");
        }
    }

    @Override
    @Transactional
    public void updateSituacao(String id, String situacao) {
        try {
            DadosNFD dadosNfd = dadosNFDRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("DadosNFD não encontrado com o ID: " + id));
            dadosNfd.setSituacao(situacao);
            // Outros campos podem ser atualizados aqui, se necessário
            dadosNFDRepository.save(dadosNfd);
        } catch (RuntimeException e) {
            throw new RuntimeException("!ERRO! Ocorreu um erro ao atualizar a situação do DadosNFD: " + e.getMessage());
        }
    }

}
