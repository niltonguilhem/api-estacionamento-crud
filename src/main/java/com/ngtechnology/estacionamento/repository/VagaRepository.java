package com.ngtechnology.estacionamento.repository;

import com.ngtechnology.estacionamento.domain.Vagas;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends CrudRepository <Vagas, Long> {

}
