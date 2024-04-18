package com.livecoding.estudos.domain.usuarios.Entidades;

import com.livecoding.estudos.domain.usuarios.DTO.DadosNfdDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Table(name = "dadosnfd")
@Entity(name = "dadosnfd")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "dadosnfd_id")
@NoArgsConstructor
public class DadosNFD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dadosnfd_id")
    private Integer dadosnfdId;

    @Column(name = "numero_nfd")
    private String numeroNfd;

    @Column(name = "numero_nfo")
    private String numeroNfo;

    @Column(name ="filial")
    private String filial;

    @Column(name="serie")
    private String serie;

    @Column(name="cte")
    private String cte;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "situacao")
    private String situacao;

    @Column(name = "cadastrado_por")
    private String cadastradoPor;

    @Column(name = "atualizado_por")
    private String atualizadoPor;


    @Column(name = "motivo_id")
    private Integer motivo;

    @Column(name = "status_nota")
    private String status;

    @Column(name = "data")
    private LocalDate data;

    @Column(name="anexo")
    private String anexo;

    public DadosNFD(@Valid DadosNfdDTO dadosNfdDTO){
        this.numeroNfd = dadosNfdDTO.numeroNfd();
        this.numeroNfo = dadosNfdDTO.numeroNfo();
        this.filial = dadosNfdDTO.filial();
        this.serie = dadosNfdDTO.serie();
        this.cte = dadosNfdDTO.cte();
        this.observacao = dadosNfdDTO.observacao();
        this.situacao = dadosNfdDTO.situacao();
        this.cadastradoPor = dadosNfdDTO.cadastradoPor();
        this.atualizadoPor = dadosNfdDTO.atualizadoPor();
        this.motivo = dadosNfdDTO.motivo();
        this.status = dadosNfdDTO.status();
        this.anexo = dadosNfdDTO.anexo();
    }
}
