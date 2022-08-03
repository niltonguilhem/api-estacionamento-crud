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


@RestController
@RequestMapping("/api/v1/estacionamento")
public class EstacionamentoController {

    @Autowired
    private VagaService service;
    /**Estudar conceito de request e response, te enviei um resumo do stackoverflow **/



    @GetMapping()
    public ResponseEntity<List<VagasResponse>> getAllVagas(){
        List<Vagas> vagasList = service.findAllVagas();
        List<VagasResponse> vagasResponseList = vagasList.stream().map(x -> new VagasResponse()
                .withBuilderVagasId(x.getIdVaga())
                .withBuilderDisponivel(x.getDisponivel())).toList();

        return new ResponseEntity<>(vagasResponseList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagasResponse> getIdVagas(@PathVariable("id") Long id){
        Vagas vagas = service.getVagaById(id);
        VagasResponse response = new VagasResponse()
                .withBuilderVagasId(vagas.getIdVaga())
                .withBuilderDisponivel(vagas.getDisponivel());


        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<VagasResponse> postVagas(@RequestBody VagasRequest vagasRequest){

        Vagas vagas = service.save(new Vagas().withBuilderDisponivel(vagasRequest.getDisponivel()));

        VagasResponse response = new VagasResponse()
                .withBuilderVagasId(vagas.getIdVaga())
                .withBuilderDisponivel(vagas.getDisponivel());

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VagasResponse> putVagas (@PathVariable("id")Long id, @RequestBody VagasRequest vagasRequest){

        Vagas vagasUpdate = service.update(new Vagas()
                .withBuilderVagasId(id)
                .withBuilderDisponivel(vagasRequest.getDisponivel()));

        VagasResponse response = new VagasResponse()
                .withBuilderVagasId(vagasUpdate.getIdVaga())
                .withBuilderDisponivel(vagasUpdate.getDisponivel());


        return new ResponseEntity<>(response,HttpStatus.OK);
    }


}



