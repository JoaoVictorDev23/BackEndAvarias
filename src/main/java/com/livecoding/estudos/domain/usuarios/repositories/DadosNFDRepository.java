package com.livecoding.estudos.domain.usuarios.repositories;

import com.livecoding.estudos.domain.usuarios.Entidades.DadosNFD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosNFDRepository extends JpaRepository<DadosNFD, String> {

    DadosNFD findByNumeroNfd(String nfd);
}
