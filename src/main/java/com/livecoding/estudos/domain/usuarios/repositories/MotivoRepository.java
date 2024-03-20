package com.livecoding.estudos.domain.usuarios.repositories;

import com.livecoding.estudos.domain.usuarios.Entidades.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotivoRepository extends JpaRepository<Motivo, String> {

    Motivo findByNomeMotivo(String nomeMotivo);
}
