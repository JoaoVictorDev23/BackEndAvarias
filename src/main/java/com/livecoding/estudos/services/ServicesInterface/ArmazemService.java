package com.livecoding.estudos.services.ServicesInterface;

import com.livecoding.estudos.domain.usuarios.DTO.ArmazemDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArmazemService {

    void createArmazem(ArmazemDTO armazemDTO);
    void deleteArmazem(String id);
    List<ArmazemDTO> getAllArmazens();
}
