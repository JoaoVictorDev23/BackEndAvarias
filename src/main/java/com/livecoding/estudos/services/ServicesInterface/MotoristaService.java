package com.livecoding.estudos.services.ServicesInterface;

import com.livecoding.estudos.domain.usuarios.DTO.MotoristaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MotoristaService {


    void createMotorista(MotoristaDTO pessoaDTO);
    List<MotoristaDTO> findAllMotorista();

    MotoristaDTO findByMotorista(String id);

    void deleteMotorista(String id);
}
