package com.ngtechnology.estacionamento.controller;

import com.ngtechnology.estacionamento.domain.Vagas;
import com.ngtechnology.estacionamento.domain.VagasRequest;
import com.ngtechnology.estacionamento.domain.VagasResponse;
import com.ngtechnology.estacionamento.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/estacionamento")
public class EstacionamentoController {

    @Autowired
    private VagaService service;
    /**Estudar conceito de request e response, te enviei um resumo do stackoverflow **/


    @GetMapping()
    public ResponseEntity<List<Vagas>> getAllVagas() {
        return new ResponseEntity<>(service.findEstacionamento(),HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<Vagas>> getIdVagas(@PathVariable("id") Long idvaga){
        return new ResponseEntity<>(service.getVagaById(idvaga),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<VagasResponse> postVagas(@RequestBody VagasRequest vagasRequest){

        Vagas vagas = service.save(new Vagas().withBuilderDisponivel(vagasRequest.getDisponivel()));

        VagasResponse response = new VagasResponse()
                .withBuilderVagasId(vagas.getIdvaga())
                .withBuilderDisponivel(vagas.getDisponivel());

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vagas> putVagas (@PathVariable ("id") Long id, @RequestBody Vagas vagas){
        Vagas v = service.update(vagas,id);
        return new ResponseEntity<>(v, HttpStatus.OK);
    }
}



