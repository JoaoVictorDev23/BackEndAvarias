package com.livecoding.estudos.domain.usuarios.repositories;

import com.livecoding.estudos.domain.usuarios.Entidades.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, String>{

    Pessoa findByPessoaCpf(String cpfPessoa);



}
