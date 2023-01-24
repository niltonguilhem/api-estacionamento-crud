package com.ngtechnology.estacionamento.controller;


import com.ngtechnology.estacionamento.model.Vagas;
import com.ngtechnology.estacionamento.model.VagasRequest;
import com.ngtechnology.estacionamento.model.VagasResponse;
import com.ngtechnology.estacionamento.handler.exception.PartnerException;
import com.ngtechnology.estacionamento.service.VagasService;
import com.ngtechnology.estacionamento.utils.VagasUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



import javax.validation.Valid;
import java.util.List;

@Api(tags = "Vagas")
@RestController
@RequestMapping("/api/v1/estacionamento")
@Validated
public class VagasController {

    private static final Logger logger = LoggerFactory.getLogger(VagasController.class);

    @Autowired
    private VagasService service;

    @ApiOperation("Lista todos as vagas.")
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

    @ApiOperation("Busca uma vaga pelo ID.")
    @GetMapping("/{id}")
    public ResponseEntity<VagasResponse> getIdVagas(@ApiParam("ID de uma vaga")@PathVariable("id") Long id)  {
            logger.info("m=getIdVaga - status=start " + id);
            Vagas vagas = service.getVagaById(id);
            VagasResponse response = new VagasResponse()
                    .withBuilderVagasId(vagas.getIdVaga())
                    .withBuilderDisponivel(vagas.getDisponivel());
            logger.info("m=getIdVaga - status=finish " + id);
            return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation("Cadastra nova vaga.")
    @PostMapping
    public ResponseEntity<VagasResponse> postVagas(@RequestBody @Valid VagasRequest vagasRequest,

                                                   @ApiParam( value = "Parceiros aceitos: Star-Park, Center-Park e Downton-Park")
                                                   @RequestHeader String partner)throws PartnerException{
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
    @ApiOperation("Editar vagas pelo ID.")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
       public ResponseEntity<VagasResponse> putVagas(@RequestHeader String partner,

                                                     @ApiParam(value = "Informar o ID para editar uma vaga")
                                                     @PathVariable("id") Long id,

                                                     @ApiParam( value = "Parceiros aceitos: Star-Park, Center-Park e Downton-Park")
                                                     @RequestBody VagasRequest vagasRequest) throws PartnerException {

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

    }
}





