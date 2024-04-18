package com.livecoding.estudos.domain.usuarios.repositories;

import com.livecoding.estudos.domain.usuarios.Entidades.Arquivos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArquivoRepository extends JpaRepository< Arquivos, Integer> {

    List<Arquivos> findAllByNumeronfd(String numeronfd);
}
