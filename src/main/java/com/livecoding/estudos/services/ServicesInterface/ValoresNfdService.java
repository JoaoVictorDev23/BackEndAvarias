package com.livecoding.estudos.services.ServicesInterface;

import com.livecoding.estudos.domain.usuarios.DTO.CriarNotaFiscalDTO;
import com.livecoding.estudos.domain.usuarios.DTO.ValoresNFDDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ValoresNfdService {

    void createValoresNfd(ValoresNFDDTO valoresNFDDTO);
    ValoresNFDDTO updateValoresNfd(ValoresNFDDTO valoresNFDDTO);

    ValoresNFDDTO findByValores(String id);
    List<CriarNotaFiscalDTO> findAll(Integer armazemId);
    void updateSituacao(String id, String situacao);


}
