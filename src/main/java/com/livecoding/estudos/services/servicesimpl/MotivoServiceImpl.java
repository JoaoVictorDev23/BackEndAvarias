package com.livecoding.estudos.services.servicesimpl;

import com.livecoding.estudos.domain.usuarios.DTO.MotivosDto;
import com.livecoding.estudos.domain.usuarios.Entidades.Motivo;
import com.livecoding.estudos.domain.usuarios.repositories.MotivoRepository;
import com.livecoding.estudos.services.ServicesInterface.MotivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class MotivoServiceImpl implements MotivoService {

    @Autowired
    MotivoRepository motivoRepository;

    @Override
    public void createMotivo(MotivosDto motivoDto) {
        Motivo existingMotivo = motivoRepository.findByNomeMotivo(motivoDto.nomeMotivo());
        if (existingMotivo != null) {
            throw new RuntimeException("Motivo já existente!");
        }

        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();


        Motivo newMotivo = new Motivo(motivoDto);
        newMotivo.setCadastradorPor(emailUsuario);
        motivoRepository.save(newMotivo);
    }


    @Override
    public void deleteMotivo(String id){
        motivoRepository.deleteById(id);
    }

    @Override
    public List<MotivosDto> findAllMotivo() {
        List<Motivo> allMotivos = motivoRepository.findAll();
        return allMotivos.stream()
                .map(MotivosDto::new)
                .collect(Collectors.toList());
    }


}
