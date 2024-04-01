package com.livecoding.estudos.domain.usuarios.Entidades;

import com.livecoding.estudos.domain.usuarios.DTO.ChatMessageDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "chat")
@Entity(name = "chatmessage")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="message")
    private String message;

    @Column(name="nfd_vinculada")
    private String nfdVinculada;

    @Column(name="user_vinculado")
    private String userVinculado;

    @Column(name="datahora")
    private LocalDateTime datahora;



    public ChatMessage(ChatMessageDTO chatMessageDTO){
        this.message = chatMessageDTO.message();
        this.nfdVinculada = chatMessageDTO.nfdVinculada();

    }



}
