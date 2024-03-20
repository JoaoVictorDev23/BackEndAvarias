package com.livecoding.estudos.domain.usuarios.repositories;

import com.livecoding.estudos.domain.usuarios.Entidades.Armazem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArmazemRepository extends JpaRepository<Armazem, String> {
   Armazem findByArmazemNome(String armazemNome);
}
