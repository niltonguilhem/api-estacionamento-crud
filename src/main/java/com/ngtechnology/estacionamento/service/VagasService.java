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

    private static final Logger logger = LoggerFactory.getLogger(VagasController.class);


    @Autowired
    private VagasRepository repository;

    public List<Vagas> getAllVagas() {
        logger.info("m=getAllVagas - status=service_Ok");
        return repository.findAll();
    }
    public Vagas getVagaById(Long idVaga) {
        logger.info("m=getVagaById - status=service_Ok");
        return repository.findById(idVaga).get();
    }
    public Optional<Vagas> getVagaByIdOptional(Long idVaga) {
        logger.info("m=getVagaByIdOptional - status=service_Ok");
        return repository.findById(idVaga);
    }

    public Vagas save(Vagas vagas) {
        logger.info("m=save - status=service_Ok");
        return repository.save(vagas);}


    public Vagas update(Vagas vagas) {
        Optional<Vagas> optional = getVagaByIdOptional(vagas.getIdVaga());
        logger.info("m=update - status=start");
        if (optional.isPresent()){
            Vagas vagasEntity = vagas;
            vagasEntity.setDisponivel(vagas.getDisponivel());
            repository.save(vagasEntity);
            logger.info("m=update - status=finish");
            return vagasEntity;
        }
        else {
            logger.warn("m=update - status=unknown id " + vagas.getIdVaga());
            throw new RuntimeException("O id informado é inexistente." );
        }
    }
}
