package com.livecoding.estudos.domain.usuarios.DTO;

import com.livecoding.estudos.domain.usuarios.Entidades.ChatMessage;

public record ChatMessageDTO(

        String message,
        String usuario,
        String nfdVinculada
) {

    public ChatMessageDTO(ChatMessage chatMessage){
        this(chatMessage.getMessage(), chatMessage.getUserVinculado(),
                chatMessage.getNfdVinculada());
    }
}
