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


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntidadeInexistenteException.class)
    public ResponseEntity<?> handleIdNaoEncontradoException(
            EntidadeInexistenteException exception, WebRequest request) {
        HttpStatus status= HttpStatus.NOT_FOUND;

        Problem problem = new Problem()
                .withBuilderStatus(status.value())
                .withBuilderType("https://ngtechnology.com.br/entidade-nao-encontrada")
                .withBuilderTitle("Entidade não encontrada")
                .withBuilderDetail(exception.getMessage());

        return handleExceptionInternal(exception, problem, new HttpHeaders(),
                status, request);
    }

    @ExceptionHandler(PartnerException.class)
    public ResponseEntity<?> handlePartnerNaoEncontradoException(
            PartnerException exceptionPartner, WebRequest request) {
        return handleExceptionInternal(exceptionPartner, exceptionPartner.getMessage(), new HttpHeaders(),
                HttpStatus.NOT_FOUND, request);

    }
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if (body == null) {
            body = new Problem()
                    .withBuilderTitle(status.getReasonPhrase())
                    .withBuilderStatus(status.value());
        } else if (body instanceof  String) {
            body = new Problem()
                    .withBuilderTitle((String) body)
                    .withBuilderStatus(status.value());
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    
}

