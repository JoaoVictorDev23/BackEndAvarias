package com.livecoding.estudos.domain.usuarios.Entidades;

import com.livecoding.estudos.domain.usuarios.DTO.PessoaDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "pessoa")
@Entity(name = "pessoa")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Pessoa {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="pessoa_id")
    private String pessoaId;
    @Column(name="pessoa_nome")
    private String pessoaNome;
    @Column(name="pessoa_cpf")
    private String pessoaCpf;
    @Column(name="pessoa_email")
    private String pessoaEmail;
    @Column(name="cadastrado_por")
    private String cadastradorPor;
    @Column(name="pessoa_debitado")
    private Float pessoaDebitado;


    public Pessoa(@Valid PessoaDTO pessoaDTO) {
        this.pessoaNome = pessoaDTO.nome();
        this.pessoaCpf = pessoaDTO.cpf();
        this.pessoaEmail = pessoaDTO.email();
        this.pessoaDebitado = pessoaDTO.debitado();

    }
}
