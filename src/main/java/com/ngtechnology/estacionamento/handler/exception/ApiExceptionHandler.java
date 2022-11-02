package com.ngtechnology.estacionamento.handler.exception;

import com.ngtechnology.estacionamento.handler.exceptionHandler.Problema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(EntidadeInexistenteException.class)
    public ResponseEntity<Problema> handlerIdNaoEncontradoException(
            EntidadeInexistenteException exception) {
        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problema);
    }

    @ExceptionHandler(PartnerException.class)
    public ResponseEntity<Problema> handlerPartnerNaoEncontradoException(
            PartnerException exceptionPartner) {
        Problema problema = Problema.builder()
                .dataHora(LocalDateTime.now())
                .mensagem(exceptionPartner.getMessage()).build();
        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problema);
    }
}

