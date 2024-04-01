package com.livecoding.estudos.services.servicesimpl;

import com.livecoding.estudos.domain.usuarios.DTO.ChatMessageDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.ChatMessage;
import com.livecoding.estudos.domain.usuarios.repositories.ChatMessageRepository;
import com.livecoding.estudos.services.ServicesInterface.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Override
    public void createChatMessage(ChatMessageDTO chatMessageDTO) {
        // Obter a data e hora atuais
        LocalDateTime dataHoraAtual = LocalDateTime.now();




        ChatMessage chatMessage = new ChatMessage(chatMessageDTO);
        String emailUsuario = SecurityContextHolder.getContext().getAuthentication().getName();

        chatMessage.setUserVinculado(emailUsuario);
        // Atribuir a data e hora atuais ao objeto ChatMessage
        chatMessage.setDatahora(dataHoraAtual);

        chatMessageRepository.save(chatMessage);

    }

    @Override
    public List<ChatMessageDTO> findByNfdVinculada(String nfd) {
        List<ChatMessage> chatMessages = chatMessageRepository.findByNfdVinculada(nfd);
        return chatMessages.stream()
                .map(ChatMessageDTO::new)
                .collect(Collectors.toList());
    }
}
