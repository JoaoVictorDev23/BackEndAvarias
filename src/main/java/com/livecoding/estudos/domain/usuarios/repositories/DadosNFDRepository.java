package com.livecoding.estudos.domain.usuarios.repositories;

import com.livecoding.estudos.domain.usuarios.Entidades.DadosNFD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DadosNFDRepository extends JpaRepository<DadosNFD, String> {


    List<DadosNFD> findAllByCadastradoPor(String email);

    DadosNFD findByNumeroNfd(String nfd);
}
