package com.livecoding.estudos.domain.usuarios.repositories;

import com.livecoding.estudos.domain.usuarios.Entidades.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, String> {

    List<ChatMessage> findByNfdVinculada(String nfdVinculada);
}
