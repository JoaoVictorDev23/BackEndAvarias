package com.livecoding.estudos.services.ServicesInterface;

import com.livecoding.estudos.domain.usuarios.DTO.ValoresNFDDTO;
import org.springframework.stereotype.Service;

@Service
public interface ValoresNfdService {

    void createValoresNfd(ValoresNFDDTO valoresNFDDTO);
    ValoresNFDDTO updateValoresNfd(ValoresNFDDTO valoresNFDDTO);

    ValoresNFDDTO findByValores(String id);

}
