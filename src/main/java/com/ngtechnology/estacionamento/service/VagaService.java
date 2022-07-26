package com.ngtechnology.estacionamento.service;

import com.ngtechnology.estacionamento.domain.Vagas;
import com.ngtechnology.estacionamento.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class VagaService {

    @Autowired
    private VagaRepository repository;

    public Iterable<Vagas> findEstacionamento() {
        return repository.findAll(); }

    public Optional<Vagas> getVagaById(Long idvaga) {
        return repository.findById(idvaga);
    }

    public Vagas save(Vagas vagas) {return repository.save(vagas);}

    public Vagas update(Vagas vagas, Long id) {
        Optional<Vagas> optional = getVagaById(id);
        if (optional.isPresent()){
            Vagas db = optional.get();
            db.setDisponivel(vagas.getDisponivel());
            repository.save(db);
            return db;
        }
        else {
            throw new RuntimeException();
        }
    }
}
