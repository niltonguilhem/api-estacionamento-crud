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

    /**
     * 1- log info status start ou finish
     * os logs da services não ajudam o senhor a detectar um bug,
     * pois aparenta que foi executado com sucesso.
     * nesses casos o senhor pode criar uma variavel, ex:
     *
     * logger.info("m=findAllVagas - status=start");
     *          List<Vagas> vagasList = repository.findAll();
     *          logger.info("m=findAllVagas - status= finish");
     *         return vagasList;
     *"Feito aguardando análise do Jr."
     *
     * 2- Padrão de log deve ser mantido em toda a estrutura do projeto.
     * "Feito aguardando análise do Jr."
     */

    public List<Vagas> findAllVagas() {
        logger.info("m=getAllVagas - status=start");
        List<Vagas> vagasList = repository.findAll();
              logger.info("m=findAllVagas - status= finish");
              return vagasList;
    }
    public Vagas getVagaById(Long idVaga) {
        logger.info("m=getVagaById - status=start " + idVaga);
        Vagas vagas = repository.findById(idVaga).get();
        logger.info("m=getVagaById - status=finish " + idVaga);
        return vagas;
    }
    public Optional<Vagas> getVagaByIdOptional(Long idVaga) {
        logger.info("m=getVagaByIdOptional - status=start " + idVaga);
        Optional<Vagas> vagasOptional = repository.findById(idVaga);
        logger.info("m=getVagaByIdOptional - status=finish " + idVaga);
        return vagasOptional;
    }

    public Vagas save(Vagas vagas) {
        logger.info("m=save - status=start");
        Vagas vagasSave = repository.save(vagas);
        logger.info("m=save - status=finish");
        return vagasSave;
    }


    public Vagas update(Vagas vagas) {
        logger.info("m=update - status=start " + vagas.getIdVaga());
        Optional<Vagas> optional = getVagaByIdOptional(vagas.getIdVaga());
        if (optional.isPresent()){
            Vagas vagasEntity = vagas;
            vagasEntity.setDisponivel(vagas.getDisponivel());
            repository.save(vagasEntity);
            logger.info("m=update - status=finish " + vagas.getIdVaga());
            return vagasEntity;
        }
        else {
            /**
             * faltou id= na mensagem de WARN
             * Warn está correto, pois não é um erro de runtime e sim um lembrete de que n saiu como esperado
              "Feito, aguardando análise do Jr." */
            logger.warn("m=update - status=warn " + vagas.getIdVaga());
            throw new RuntimeException("O id informado é inexistente." );
        }
    }
}
