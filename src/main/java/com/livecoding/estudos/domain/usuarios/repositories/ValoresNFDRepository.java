package com.livecoding.estudos.domain.usuarios.repositories;

import com.livecoding.estudos.domain.usuarios.Entidades.ValoresNFD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValoresNFDRepository extends JpaRepository<ValoresNFD, String> {

    ValoresNFD findByNumeronfd(String numeronfd);

}
