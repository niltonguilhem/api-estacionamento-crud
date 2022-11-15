package com.ngtechnology.estacionamento.handler.exceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.ngtechnology.estacionamento.handler.exception.EntidadeInexistenteException;
import com.ngtechnology.estacionamento.handler.exception.PartnerException;
import com.ngtechnology.estacionamento.handler.exception.ProblemType;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException  exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(exception);

        if (rootCause instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        }

        ProblemType problemType = ProblemType.CORPO_DO_JSON_ESTA_INCORRETO;
        String detail = "O corpo da requisição está invalido. Verifique erro de sintaxe.";

        Problem problem = createWithBuilderProblem(status, problemType, detail);

        return handleExceptionInternal(exception, problem, headers ,status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException exception,
                                                                HttpHeaders headers, HttpStatus status,
                                                                WebRequest request){
        //exception.getPath().forEach(ref -> System.out.println(ref.getFieldName())); //para fazer teste e identificar as referências.

        String path = exception.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining());// Caso eu queira realizar um join concatenando com um complemento do id é só inserir um ponto entre aspas duplas dentro do parenteses.

        ProblemType problemType = ProblemType.CORPO_DO_JSON_ESTA_INCORRETO;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s, que é de um tipo inválido. " +
                "Corrija e informe um valor compatível com o tipo %s.", path, exception.getValue(),
                exception.getTargetType().getSimpleName());

        Problem problem = createWithBuilderProblem(status, problemType, detail);

        return handleExceptionInternal(exception,problem, headers,status,request);

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

