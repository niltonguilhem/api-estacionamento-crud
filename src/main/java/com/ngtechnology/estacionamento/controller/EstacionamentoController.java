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


    /** modificar a nomenclatura do método, talvez para getAllVagas **/
    @GetMapping()
    public Iterable<Vagas> get() {
        return service.findEstacionamento();}

    /** modificar a nomenclatura do método, talvez para getIdVagas **/
    @GetMapping("/{id}")
    public Optional<Vagas> get(@PathVariable("id") Long idvaga) {
        return service.getVagaById(idvaga);
    }

    /** modificar a nomenclatura do método, talvez para postVagas, lembrando que as nomenclaturas save, delete, find, são mais recorrentes
     * nas services que interagem com o banco de dados **/
    @PostMapping
    public ResponseEntity<Vagas> save(@RequestBody Vagas vagas){
        return new ResponseEntity<>(service.save(vagas),HttpStatus.CREATED);
    }

    /** modificar a nomenclatura do método, talvez para putVagas **/
    @PutMapping("/{id}")
    public ResponseEntity<Vagas> put (@PathVariable ("id") Long id, @RequestBody Vagas vagas){
        Vagas v = service.update(vagas,id);
        return new ResponseEntity<>(v, HttpStatus.ALREADY_REPORTED);
    }


}



