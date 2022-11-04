package com.ngtechnology.estacionamento.handler.exceptionHandler;

import com.ngtechnology.estacionamento.handler.exception.EntidadeInexistenteException;
import com.ngtechnology.estacionamento.handler.exception.PartnerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;



@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(EntidadeInexistenteException.class)
    public ResponseEntity<Problema> handlerIdNaoEncontradoException(
            EntidadeInexistenteException exception) {
        Problema problema = new Problema()
                .withBuilderDataHora(LocalDateTime.now())
                .withBuilderMensagem(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problema);
    }

    @ExceptionHandler(PartnerException.class)
    public ResponseEntity<Problema> handlerPartnerNaoEncontradoException(
            PartnerException exceptionPartner) {
        Problema problema = new Problema()
                .withBuilderDataHora(LocalDateTime.now())
                .withBuilderMensagem(exceptionPartner.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(problema);
    }
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Problema> handlerHttpMediaTypeNotSupportedException(){
        Problema problema = new Problema()
                .withBuilderDataHora(LocalDateTime.now())
                .withBuilderMensagem("O tipo de mídia não é aceito.");
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                .body(problema);
    }
}

