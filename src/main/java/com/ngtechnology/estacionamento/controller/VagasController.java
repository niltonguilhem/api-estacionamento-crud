package com.ngtechnology.estacionamento.controller;


import com.ngtechnology.estacionamento.domain.Vagas;
import com.ngtechnology.estacionamento.domain.VagasRequest;
import com.ngtechnology.estacionamento.domain.VagasResponse;
import com.ngtechnology.estacionamento.handler.exception.EntidadeInexistenteException;
import com.ngtechnology.estacionamento.handler.exception.PartnerException;
import com.ngtechnology.estacionamento.service.VagasService;
import com.ngtechnology.estacionamento.utils.VagasUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.el.EvaluationListener;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/v1/estacionamento")
@ControllerAdvice
@Validated
public class VagasController {

    private static final Logger logger = LoggerFactory.getLogger(VagasController.class);

    @Autowired
    private VagasService service;

    @GetMapping()
    public ResponseEntity<List<VagasResponse>> getAllVagas() {
        logger.info("m=getAllVagas - status=start");
        List<Vagas> vagasList = service.findAllVagas();
        List<VagasResponse> vagasResponseList = vagasList.stream().map(x -> new VagasResponse()
                .withBuilderVagasId(x.getIdVaga())
                .withBuilderDisponivel(x.getDisponivel())).toList();
        logger.info("m=getAllVagas - status=finish");
        return new ResponseEntity<>(vagasResponseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagasResponse> getIdVagas(@PathVariable("id") Long id) {
        try {
            logger.info("m=getIdVaga - status=start " + id);
            Vagas vagas = service.getVagaById(id);
            VagasResponse response = new VagasResponse()
                    .withBuilderVagasId(vagas.getIdVaga())
                    .withBuilderDisponivel(vagas.getDisponivel());
            logger.info("m=getIdVaga - status=finish " + id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NoSuchElementException idNull) {
            logger.warn("m=getIdVaga - status=warn " + id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
        @PostMapping
        public ResponseEntity<VagasResponse> postVagas(@RequestBody VagasRequest vagasRequest,
                                                       @RequestHeader String partner)throws Exception{
            VagasUtils.validatedHeader(partner);
            ResponseEntity<VagasResponse> result;
            logger.info("m=postVagas - status=start " + partner);
            Vagas vagas = service.save(new Vagas()
                    .withBuilderDisponivel(vagasRequest.getDisponivel()));
            VagasResponse response = new VagasResponse()
                    .withBuilderVagasId(vagas.getIdVaga())
                    .withBuilderDisponivel(vagas.getDisponivel());
            result = new ResponseEntity<>(response, HttpStatus.CREATED);
            logger.info("m=postVagas - status=finish " + partner);
            return result;
        }

        @PutMapping("/{id}")
        public ResponseEntity<VagasResponse> putVagas(@RequestHeader String partner,
                                                      @PathVariable("id") Long id,
                                                      @RequestBody VagasRequest vagasRequest) throws PartnerException {
        try {
            VagasUtils.validatedHeader(partner);
            logger.info("m=putVagas - status=start " + id + " " + partner);
            Vagas vagasUpdate = service.update(new Vagas()
                    .withBuilderVagasId(id)
                    .withBuilderDisponivel(vagasRequest.getDisponivel()));

            VagasResponse response = new VagasResponse()
                    .withBuilderVagasId(vagasUpdate.getIdVaga())
                    .withBuilderDisponivel(vagasUpdate.getDisponivel());
            logger.info("m=putVagas - status=finish " + id + " " + partner);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (EntidadeInexistenteException response) {
            return ResponseEntity.notFound().build();
        }


        }
}





