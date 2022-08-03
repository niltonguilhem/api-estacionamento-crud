package com.ngtechnology.estacionamento.service;

import com.ngtechnology.estacionamento.domain.Vagas;
import com.ngtechnology.estacionamento.domain.VagasRequest;
import com.ngtechnology.estacionamento.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

/** Estudar sobre SOLID,
 * enviar no chat o site para o Sr ler
 **/
@Service
public class VagaService {

    @Autowired
    private VagaRepository repository;

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
