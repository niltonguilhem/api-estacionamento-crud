package com.ngtechnology.estacionamento.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND)//, reason = "Entidade não encontrada.")
public class EntidadeInexistenteException extends ResponseStatusException {

    public EntidadeInexistenteException(HttpStatus status, String mensagem) {
        super(status, mensagem);
    }

    public EntidadeInexistenteException(String mensagem) {
        this(HttpStatus.NOT_FOUND,mensagem);
    }


}
