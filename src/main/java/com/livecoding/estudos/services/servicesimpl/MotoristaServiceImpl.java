package com.livecoding.estudos.services.servicesimpl;

import com.livecoding.estudos.Errors.ErrorResponse;
import com.livecoding.estudos.domain.usuarios.DTO.MotoristaDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.Motorista;
import com.livecoding.estudos.domain.usuarios.repositories.MotoristaRepository;
import com.livecoding.estudos.services.ServicesInterface.MotoristaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MotoristaServiceImpl implements MotoristaService {

    @Autowired
    MotoristaRepository motoristaRepository;

    @Override
    public void createMotorista(MotoristaDTO motoristaDTO) {
        Motorista existingMotorista = this.motoristaRepository.findByMotoristaCpf(motoristaDTO.cpf());
        if(existingMotorista != null){
            throw  new RuntimeException("Motorista já existente!");

        }
        Motorista newMotorista = new Motorista(motoristaDTO);
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        newMotorista.setCadastradorPor(emailUsuario);

        motoristaRepository.save(newMotorista);
    }

    @Override
    public void deleteMotorista(String id){
        motoristaRepository.deleteById(id);
    }

    @Override
    public MotoristaDTO findByMotorista(String id){
        Optional<Motorista> motoristaOptional = motoristaRepository.findById(id);
        Motorista motorista = motoristaOptional.orElseThrow(() -> new EntityNotFoundException("Colaborador não localizado"));
        return new MotoristaDTO(motorista);
    }

    @Override
    public List<MotoristaDTO> findAllMotorista() {
        List<Motorista> allMotoristas = motoristaRepository.findAll();
        return allMotoristas.stream()
                .map(MotoristaDTO::new)
                .collect(Collectors.toList());
    }
}
