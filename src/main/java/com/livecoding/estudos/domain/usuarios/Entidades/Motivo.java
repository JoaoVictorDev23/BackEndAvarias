package com.livecoding.estudos.domain.usuarios.Entidades;


import com.livecoding.estudos.domain.usuarios.DTO.MotivosDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


@Table(name = "motivos")
@Entity(name = "motivos")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "motivoId")
@NoArgsConstructor
public class Motivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "motivo_id")
    private Integer motivoId;

    @Column(name = "nome_motivo")
    private String nomeMotivo;

    @Column(name = "descri_motivo")
    private String descriMotivo;

    @Column(name="cadastrado_por")
    private String cadastradorPor;

    // Consider usar um único construtor com campos obrigatórios:


    public Motivo(@Valid MotivosDto requestMotivo){
        this.nomeMotivo = requestMotivo.nomeMotivo();
        this.descriMotivo = requestMotivo.descriMotivo();
        this.cadastradorPor = requestMotivo.cadastradoPor();
    }

}
