package com.ngtechnology.estacionamento.handler.exceptionHandler;

import com.ngtechnology.estacionamento.handler.exception.EntidadeInexistenteException;
import com.ngtechnology.estacionamento.handler.exception.PartnerException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;



@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntidadeInexistenteException.class)
    public ResponseEntity<?> handlerIdNaoEncontradoException(
            EntidadeInexistenteException exception, WebRequest request) {
        return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(PartnerException.class)
    public ResponseEntity<?> handlerPartnerNaoEncontradoException(
            PartnerException exceptionPartner, WebRequest request) {
        return handleExceptionInternal(exceptionPartner, exceptionPartner.getMessage(), new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);

    }
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if (body == null) {
            body = new Problema()
                    .withBuilderDataHora(LocalDateTime.now())
                    .withBuilderMensagem(status.getReasonPhrase());
        } else if (body instanceof  String) {
            body = new Problema()
                    .withBuilderDataHora(LocalDateTime.now())
                    .withBuilderMensagem((String) body);
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}

