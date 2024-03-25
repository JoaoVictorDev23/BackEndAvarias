package com.livecoding.estudos.services.ServicesInterface;

import com.livecoding.estudos.Errors.ErrorResponse;
import com.livecoding.estudos.domain.usuarios.DTO.MotivosDto;
import com.livecoding.estudos.domain.usuarios.Entidades.Motivo;
import com.livecoding.estudos.domain.usuarios.repositories.MotivoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public interface MotivoService {


    void createMotivo(MotivosDto motivosDto);
    void deleteMotivo(String id);

    List<MotivosDto> findAllMotivo();

    MotivosDto findById(String id);

}
