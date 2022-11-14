package com.ngtechnology.estacionamento.handler.exceptionHandler;

import com.ngtechnology.estacionamento.handler.exception.EntidadeInexistenteException;
import com.ngtechnology.estacionamento.handler.exception.PartnerException;
import com.ngtechnology.estacionamento.handler.exception.ProblemType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException  exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {

        ProblemType problemType = ProblemType.CORPO_DO_JSON_ESTA_INCORRETO;
        String detail = "O corpo da requisição está invalido. Verifique erro de sintaxe.";

        Problem problem = createWithBuilderProblem(status, problemType, detail);

        return handleExceptionInternal(exception, problem, new HttpHeaders(),
                status, request);
    }

    @ExceptionHandler(EntidadeInexistenteException.class)
    public ResponseEntity<?> handleIdNaoEncontradoException(
            EntidadeInexistenteException exception, WebRequest request) {
        HttpStatus status= HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        String detail = exception.getMessage();

        Problem problem = createWithBuilderProblem(status, problemType, detail);

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
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers,
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
        return super.handleExceptionInternal(exception, body, headers, status, request);
    }

    private Problem createWithBuilderProblem(HttpStatus status, ProblemType problemType,
                                             String detail){
        return new Problem()
                .withBuilderStatus(status.value())
                .withBuilderType(problemType.getUri())
                .withBuilderTitle(problemType.getTitle())
                .withBuilderDetail(detail);
    }
}

