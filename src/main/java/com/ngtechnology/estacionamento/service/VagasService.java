package com.ngtechnology.estacionamento.service;

import com.ngtechnology.estacionamento.domain.Vagas;
import com.ngtechnology.estacionamento.repository.VagasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class VagasService {

    @Autowired
    private VagasRepository repository;

    public List<Vagas> findAllVagas() {
        return repository.findAll();
    }
    //Arrumar o nome desse método
    public Vagas getVagaById(Long idVaga) {
        return repository.findById(idVaga).get();
    }
    public Optional<Vagas> getVagaByIdOptional(Long idVaga) {
        return repository.findById(idVaga);
    }

    public Vagas save(Vagas vagas) {return repository.save(vagas);}

    public Vagas update(Vagas vagas) {
        Optional<Vagas> optional = getVagaByIdOptional(vagas.getIdVaga());
        if (optional.isPresent()){
            Vagas vagasEntity = vagas;
            vagasEntity.setDisponivel(vagas.getDisponivel());
            repository.save(vagasEntity);
            return vagasEntity;
        }
        else {
            throw new RuntimeException();
        }
    }
}
