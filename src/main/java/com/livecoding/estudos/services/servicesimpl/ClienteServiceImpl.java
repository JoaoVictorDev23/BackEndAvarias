package com.livecoding.estudos.services.servicesimpl;

import com.livecoding.estudos.domain.usuarios.DTO.ClienteDTO;
import com.livecoding.estudos.domain.usuarios.DTO.PessoaDTO;
import com.livecoding.estudos.domain.usuarios.DTO.ValoresNFDDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.Cliente;
import com.livecoding.estudos.domain.usuarios.Entidades.Pessoa;
import com.livecoding.estudos.domain.usuarios.Entidades.ValoresNFD;
import com.livecoding.estudos.domain.usuarios.repositories.ClienteRepository;
import com.livecoding.estudos.services.ServicesInterface.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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


    @Override
    public ClienteDTO findById(String id){
        Optional<Cliente> Optionalcliente = clienteRepository.findById(id);
        Cliente cliente = Optionalcliente.orElseThrow(()-> new EntityNotFoundException("Cliente inexistente."));
        return new ClienteDTO(cliente);
    }

    @Override
    public void updateDebitado(String id, Float debitado) {
        try {
            Cliente Optionalcliente = clienteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado com o ID: " + id));
            Optionalcliente.setDebitado(debitado);
            // Outros campos podem ser atualizados aqui, se necessário
            clienteRepository.save(Optionalcliente);
        } catch (RuntimeException e) {
            throw new RuntimeException("!ERRO! Ocorreu um erro ao atualizar!" + e.getMessage());
        }
    }



}
