package com.ngtechnology.estacionamento.repository;

import com.ngtechnology.estacionamento.domain.Vagas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<Vagas, Long> {

}
