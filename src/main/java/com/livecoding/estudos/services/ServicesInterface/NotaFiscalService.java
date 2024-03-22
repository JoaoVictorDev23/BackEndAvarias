package com.livecoding.estudos.services.ServicesInterface;

import com.livecoding.estudos.domain.usuarios.DTO.CriarNotaFiscalDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.DadosNFD;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotaFiscalService {
    void criarNotaFiscal(CriarNotaFiscalDTO criarNotaFiscalDTO);

    List<CriarNotaFiscalDTO> getAllNotasFiscais();

    List<CriarNotaFiscalDTO> findAll();

}