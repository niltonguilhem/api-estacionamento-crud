package com.ngtechnology.estacionamento.handler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)//, reason = "Entidade não encontrada.")
public class EntidadeInexistenteException extends RuntimeException {

    public EntidadeInexistenteException(String mensagem) {
        super(mensagem);
    }


}
