package com.livecoding.estudos.domain.usuarios.Entidades;


import com.livecoding.estudos.domain.usuarios.DTO.ValoresNFDDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;

@Table(name = "valoresnfd")
@Entity(name = "valoresnfd")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "valoresnfd_id")
@NoArgsConstructor
public class ValoresNFD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "valoresnfd_id")
    private Integer valoresNFDID;

    @Column(name= "valor_venda")
    private Float valorVenda;
    @Column(name="valor_prejuizo")
    private Float valorPrejuizo;
    @Column(name="valor_armazem")
    private Float valorArmazem;
    @Column(name="situacao")
    private String situacaoValores;

    @Column(name ="armazem_id")
    private Integer armazem;

    @Column(name ="motorista_id")
    private String motorista;

    @Column(name ="comprador_id")
    private String comprador;

    @Column(name ="cliente_id")
    private Integer cliente;


    @Column(name = "cadastrado_por")
    private String cadastradoPor;

    @Column(name = "atualizado_por")
    private String atualizadoPor;


    @Column(name = "valores_nfd")
    private String numeronfd;



    @Column(name="debitado_cliente")
    private Float debitadoCliente;
    @Column(name="debitado_motorista")
    private Float debitadoMotorista;

    @Column(name="data")
    private LocalDate data;

    public ValoresNFD(@Valid ValoresNFDDTO valoresNFDDTO){
        this.valorVenda = valoresNFDDTO.valorVenda();
        this.valorArmazem = valoresNFDDTO.valorArmazem();
        this.valorPrejuizo = valoresNFDDTO.valorPrejuizo();
        this.situacaoValores = valoresNFDDTO.situacaoValores();
        this.armazem = valoresNFDDTO.armazem();
        this.comprador = valoresNFDDTO.comprador();
        this.motorista = valoresNFDDTO.motorista();
        this.cliente = valoresNFDDTO.cliente();
        this.cadastradoPor = valoresNFDDTO.cadastradoPor();
        this.atualizadoPor = valoresNFDDTO.atualizadoPor();
        this.numeronfd = valoresNFDDTO.numeronfd();
        this.debitadoCliente = valoresNFDDTO.debitadoCliente();
        this.debitadoMotorista = valoresNFDDTO.debitadoMotorista();
    }


}
