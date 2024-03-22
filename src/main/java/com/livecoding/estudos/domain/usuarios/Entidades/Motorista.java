package com.livecoding.estudos.domain.usuarios.Entidades;

import com.livecoding.estudos.domain.usuarios.DTO.MotoristaDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "motorista")
@Entity(name = "motorista")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Motorista {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="motorista_id")
    private String motoristaId;
    @Column(name="motorista_nome")
    private String motoristaNome;
    @Column(name="motorista_cpf")
    private String motoristaCpf;
    @Column(name="motorista_email")
    private String motoristaEmail;
    @Column(name="cadastrado_por")
    private String cadastradorPor;
    @Column(name="motorista_debitado")
    private Float motoristaDebitado;


    public Motorista(@Valid MotoristaDTO motoristaDTO) {
        this.motoristaNome = motoristaDTO.nome();
        this.motoristaCpf = motoristaDTO.cpf();
        this.motoristaEmail = motoristaDTO.email();
        this.motoristaDebitado = motoristaDTO.debitado();

    }
}
