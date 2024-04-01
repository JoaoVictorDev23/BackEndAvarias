package com.livecoding.estudos.domain.usuarios.DTO;

import com.livecoding.estudos.domain.usuarios.Entidades.ChatMessage;

import java.time.LocalDateTime;
import java.util.Date;

public record ChatMessageDTO(

        String message,
        String usuario,
        String nfdVinculada,
        LocalDateTime datahora
) {

    public ChatMessageDTO(ChatMessage chatMessage){
        this(chatMessage.getMessage(), chatMessage.getUserVinculado(),
                chatMessage.getNfdVinculada(), chatMessage.getDatahora());
    }
}
