package com.ngtechnology.estacionamento.service;


import com.ngtechnology.estacionamento.domain.Vagas;
import com.ngtechnology.estacionamento.handler.exception.EntidadeInexistenteException;
import com.ngtechnology.estacionamento.repository.VagasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@Service
public class VagasService {

    private static final Logger logger = LoggerFactory.getLogger(VagasService.class);
    @Autowired
    private VagasRepository repository;

    public List<Vagas> findAllVagas() {
        logger.info("m=findAllVagas - status=start");

            List<Vagas> vagasList = repository.findAll();
            logger.info("m=findAllVagas - status= finish");
            return vagasList;
    }
    public Vagas getVagaById(Long idVaga) {
        logger.info("m=getVagaById - status=start " + idVaga);
        Vagas vagas = repository.findById(idVaga)
                .orElseThrow(() -> new EntidadeInexistenteException(
                        String.format("Não existe vaga com este id %d",idVaga)));
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
            logger.warn("m=update - status=warn " + vagas.getIdVaga());
            throw new EntidadeInexistenteException(String.format
                    ("O id %d informado é inexistente.", vagas.getIdVaga()));
        }
    }
}
