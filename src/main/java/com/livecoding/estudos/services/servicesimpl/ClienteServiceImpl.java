package com.livecoding.estudos.services.servicesimpl;

import com.livecoding.estudos.domain.usuarios.DTO.ClienteDTO;
import com.livecoding.estudos.domain.usuarios.DTO.PessoaDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.Cliente;
import com.livecoding.estudos.domain.usuarios.Entidades.Pessoa;
import com.livecoding.estudos.domain.usuarios.repositories.ClienteRepository;
import com.livecoding.estudos.services.ServicesInterface.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void createCliente(ClienteDTO clienteDTO){
        Cliente existingcliente = clienteRepository.findByClienteCnpj(clienteDTO.cnpj());

        if(existingcliente != null){
            throw  new RuntimeException("Cliente já existente!");
        }
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        Cliente newCliente = new Cliente(clienteDTO);
        newCliente.setCadastradorPor(emailUsuario);
        clienteRepository.save(newCliente);
    }
    @Override
    public List<ClienteDTO> findAllCliente() {
        List<Cliente> allClientes = clienteRepository.findAll();
        return allClientes.stream()
                .map(ClienteDTO::new)
                .collect(Collectors.toList());
    }


}
