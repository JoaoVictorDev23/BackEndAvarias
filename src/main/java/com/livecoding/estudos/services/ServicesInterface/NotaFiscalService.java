package com.livecoding.estudos.services.ServicesInterface;

import com.livecoding.estudos.domain.usuarios.DTO.CriarNotaFiscalDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotaFiscalService {
    void criarNotaFiscal(CriarNotaFiscalDTO criarNotaFiscalDTO);

    List<CriarNotaFiscalDTO> getAllNotasFiscais();

}