package com.ngtechnology.estacionamento.repository;

import com.ngtechnology.estacionamento.model.Vagas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagasRepository extends JpaRepository<Vagas, Long> {

}
