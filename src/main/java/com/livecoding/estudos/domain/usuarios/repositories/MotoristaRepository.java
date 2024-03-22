package com.livecoding.estudos.domain.usuarios.repositories;

import com.livecoding.estudos.domain.usuarios.Entidades.Motorista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoristaRepository extends JpaRepository<Motorista, String> {

    Motorista findByMotoristaCpf(String cpfMotorista);



}
