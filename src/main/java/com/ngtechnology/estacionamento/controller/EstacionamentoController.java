package com.ngtechnology.estacionamento.controller;

import com.ngtechnology.estacionamento.domain.Vagas;
import com.ngtechnology.estacionamento.service.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/estacionamento")
public class EstacionamentoController {
    @Autowired
    private VagaService service;

    @GetMapping()
    public Iterable<Vagas> get() {
        return service.findEstacionamento();}

    @GetMapping("/{id}")
    public Optional<Vagas> get(@PathVariable("id") Long idvaga) {
        return service.getVagaById(idvaga);
    }

    @PostMapping
    public ResponseEntity<Vagas> save(@RequestBody Vagas vagas){
        return new ResponseEntity<>(service.save(vagas),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vagas> put (@PathVariable ("id") Long id, @RequestBody Vagas vagas){
        Vagas v = service.update(vagas,id);
        return new ResponseEntity<>(v, HttpStatus.ALREADY_REPORTED);
    }


}



