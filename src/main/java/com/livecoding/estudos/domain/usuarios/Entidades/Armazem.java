package com.livecoding.estudos.domain.usuarios.Entidades;

import com.livecoding.estudos.domain.usuarios.DTO.ArmazemDTO;
import com.livecoding.estudos.domain.usuarios.DTO.MotivosDto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

@Table(name = "armazem")
@Entity(name = "armazem")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Armazem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "armazem_id")
    private Integer armazemId;
    @Column(name = "armazem_nome")
    private String armazemNome;
    @Column(name = "armazem_filial")
    private String armazemFilial;
    @Column(name = "armazem_endereco")
    private String armazemEndereco;
    @Column(name="cadastrado_por")
    private String cadastradorPor;

    public Armazem(@Valid ArmazemDTO armazemDTO){
        this.armazemNome = armazemDTO.armazemNome();
        this.armazemEndereco = armazemDTO.armazemFilial();
        this.armazemFilial = armazemDTO.armazemEndereco();
    }


}