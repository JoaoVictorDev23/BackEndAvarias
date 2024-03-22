package com.livecoding.estudos.controllers;


import com.livecoding.estudos.Errors.ErrorResponse;
import com.livecoding.estudos.domain.usuarios.DTO.ChatMessageDTO;
import com.livecoding.estudos.domain.usuarios.DTO.DadosNfdDTO;
import com.livecoding.estudos.domain.usuarios.repositories.ChatMessageRepository;
import com.livecoding.estudos.services.ServicesInterface.ChatMessageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatMessageController {

    @Autowired
    ChatMessageService chatMessageService;

    @PostMapping
    public ResponseEntity createMessage(@RequestBody ChatMessageDTO chatMessageDTO){
        try {
            chatMessageService.createChatMessage(chatMessageDTO);
            return ResponseEntity.ok().build();
        }
        catch (RuntimeException e){
            ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }

    }

    @GetMapping("{nfd}")
    public ResponseEntity<List<ChatMessageDTO>> getMessagesByNfd(@PathVariable String nfd) {
        try {
            List<ChatMessageDTO> messages = chatMessageService.findByNfdVinculada(nfd);
            return ResponseEntity.ok(messages);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}
