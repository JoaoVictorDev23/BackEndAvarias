package com.livecoding.estudos.domain.usuarios.repositories;

import com.livecoding.estudos.domain.usuarios.Entidades.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutosRepository extends JpaRepository<Produtos, String> {
    Produtos findByArmazemId(Integer armazemid);
    List<Produtos> findAllByArmazemId(Integer armazemId);

    List<Produtos> findAllByNumeronfd(String numero_nfd);

}
