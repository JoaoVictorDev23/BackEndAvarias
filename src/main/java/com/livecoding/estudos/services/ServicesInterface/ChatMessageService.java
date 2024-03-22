package com.livecoding.estudos.services.ServicesInterface;

import com.livecoding.estudos.domain.usuarios.DTO.ChatMessageDTO;
import com.livecoding.estudos.domain.usuarios.Entidades.ChatMessage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChatMessageService {


    List<ChatMessageDTO> findByNfdVinculada(String nfd);
    void createChatMessage(ChatMessageDTO chatMessageDTO);
}
