package com.livecoding.estudos.domain.usuarios.Entidades;


import com.livecoding.estudos.domain.usuarios.DTO.UsuarioDTO;
import com.livecoding.estudos.domain.usuarios.enums.perfis;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;



@Table(name="usuarios")
@Entity(name="usuarios")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@NoArgsConstructor

public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;
    @Column(name = "cpf")
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil", nullable = false)
    private perfis perfis;

    public Usuario(@Valid UsuarioDTO requestUsuario){
        this.name = requestUsuario.name();
        this.email = requestUsuario.email();
        this.senha = requestUsuario.senha();
        this.perfis= requestUsuario.perfis();
        this.cpf= requestUsuario.cpf();
    }
    public Usuario(String email, String senha,perfis perfil){
        this.email = email;
        this.senha = senha;
        this.perfis = perfil;

    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

