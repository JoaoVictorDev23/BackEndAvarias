package com.livecoding.estudos.services.ServicesInterface;

import com.livecoding.estudos.domain.usuarios.DTO.DadosNfdDTO;
import org.springframework.stereotype.Service;

@Service
public interface DadosNFDService {

    void createDados(DadosNfdDTO dadosNfdDTO);
    DadosNfdDTO updateDadosNfd(DadosNfdDTO dadosNfdDTO);



}
