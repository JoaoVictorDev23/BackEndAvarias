package com.livecoding.estudos.domain.usuarios.Entidades;

import com.livecoding.estudos.domain.usuarios.DTO.ClienteDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;


@Table(name = "cliente")
@Entity(name = "cliente")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "cliente_id")
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cliente_id")
    private Integer cliente_id;
    @Column(name="cliente_nome")
    private String cliente_nome;
    @Column(name="cliente_cnpj")
    private String clienteCnpj;
    @Column(name="valor_debitado")
    private Float debitado;
    @Column(name="cadastrado_por")
    private String cadastradorPor;

    public Cliente(@Valid ClienteDTO clientedto){
        this.clienteCnpj = clientedto.cnpj();
        this.cliente_nome = clientedto.nome();
        this.debitado = clientedto.debitado();
    }
}
