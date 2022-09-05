package com.ngtechnology.estacionamento.controller;


import com.ngtechnology.estacionamento.domain.Vagas;
import com.ngtechnology.estacionamento.domain.VagasRequest;
import com.ngtechnology.estacionamento.domain.VagasResponse;
import com.ngtechnology.estacionamento.service.VagasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
;

import java.util.List;
import java.util.NoSuchElementException;



@RestController
@RequestMapping("/api/v1/estacionamento")
@ControllerAdvice
public class VagasController {

    private static final Logger logger = LoggerFactory.getLogger(VagasController.class);

    @Autowired
    private VagasService service;

    @GetMapping()
    public ResponseEntity<List<VagasResponse>> getAllVagas(){
        logger.info("Consultando vagas...");
        List<Vagas> vagasList = service.findAllVagas();
        List<VagasResponse> vagasResponseList = vagasList.stream().map(x -> new VagasResponse()
                .withBuilderVagasId(x.getIdVaga())
                .withBuilderDisponivel(x.getDisponivel())).toList();

        return new ResponseEntity<>(vagasResponseList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagasResponse> getIdVagas(@PathVariable("id") Long id) {
        try {
            Vagas vagas = service.getVagaById(id);
            logger.info("Vaga com id: " + id + " encotrada" );
            VagasResponse response = new VagasResponse()
                    .withBuilderVagasId(vagas.getIdVaga())
                    .withBuilderDisponivel(vagas.getDisponivel());
        return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (NoSuchElementException idNull) {
            logger.warn("Vaga com id: " + id + " não encontrada");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<VagasResponse> postVagas(@RequestBody  VagasRequest vagasRequest) {
        ResponseEntity<VagasResponse> result;

            Vagas vagas = service.save(new Vagas().withBuilderDisponivel(vagasRequest.getDisponivel()));
            logger.info("Vaga cadastrada com sucesso!!!");
            VagasResponse response = new VagasResponse()
                    .withBuilderVagasId(vagas.getIdVaga())
                    .withBuilderDisponivel(vagas.getDisponivel());
            result = new ResponseEntity<>(response, HttpStatus.CREATED);

        return result;
    }
    @PutMapping("/{id}")
    public ResponseEntity<VagasResponse> putVagas (@PathVariable("id")Long id, @RequestBody VagasRequest vagasRequest){

            Vagas vagasUpdate = service.update(new Vagas()
                    .withBuilderVagasId(id)
                    .withBuilderDisponivel(vagasRequest.getDisponivel()));

            VagasResponse response = new VagasResponse()
                    .withBuilderVagasId(vagasUpdate.getIdVaga())
                    .withBuilderDisponivel(vagasUpdate.getDisponivel());
                    logger.info("Vaga com id: " + id + " atualizado");
            return new ResponseEntity<>(response, HttpStatus.OK);

    }


}



