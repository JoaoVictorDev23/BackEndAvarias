package com.livecoding.estudos.services.ServicesInterface;

import com.livecoding.estudos.domain.usuarios.DTO.ClienteDTO;
import com.livecoding.estudos.domain.usuarios.DTO.PessoaDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClienteService {
    void createCliente(ClienteDTO clienteDTO);
    List<ClienteDTO> findAllCliente();

}
