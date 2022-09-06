package com.ngtechnology.estacionamento.service;

import com.ngtechnology.estacionamento.controller.VagasController;
import com.ngtechnology.estacionamento.domain.Vagas;
import com.ngtechnology.estacionamento.repository.VagasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class VagasService {

    /**
     * Seguir o exemplo de logs comentado na controller e colocar na service
     */
    private static final Logger logger = LoggerFactory.getLogger(VagasController.class);


    @Autowired
    private VagasRepository repository;

    public List<Vagas> findAllVagas() {
        return repository.findAll();
    }
    //Arrumar o nome desse método
    public Vagas getVagaById(Long idVaga) {
        //TODO implementar log na service...
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
            logger.warn("O id: " + vagas.getIdVaga() + " informado é inexistente");
            throw new RuntimeException("O id informado é inexistente." );
        }
    }
}
