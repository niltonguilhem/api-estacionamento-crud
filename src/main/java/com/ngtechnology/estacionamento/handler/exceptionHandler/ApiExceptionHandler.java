package com.ngtechnology.estacionamento.handler.exceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.ngtechnology.estacionamento.handler.exception.EntidadeInexistenteException;
import com.ngtechnology.estacionamento.handler.exception.PartnerException;
import com.ngtechnology.estacionamento.handler.exception.ProblemType;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.ngtechnology.estacionamento.handler.exception.ProblemType.ENTIDADE_NAO_ENCONTRADA;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema." +
            "Tente novamente e se o problema persistir, entre em contato com o administrador " +
            "do sistema";

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Throwable rootCause = ExceptionUtils.getRootCause(exception);

        if (rootCause instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException) rootCause, headers, status, request);
        }

        ProblemType problemType = ENTIDADE_NAO_ENCONTRADA;
        String detail = "O corpo da requisição está invalido. Verifique erro de sintaxe.";

        BindingResult bindingResult = exception.getBindingResult();

        List<Problem.Field>problemFields = bindingResult.getFieldErrors().stream()
                .map(fieldError -> {
                    String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());

                    return new Problem.Field()
                            .withBuilderName(fieldError.getField())
                            .withBuilderUserMessage(message);
                })
                .collect(Collectors.toList());

        Problem problem = createWithBuilderProblem (status, problemType, detail)
                .withBuilderUserMessage("O campo não pode ser nulo!")
                .withBuilderFields(problemFields);

        return handleExceptionInternal(exception, problem, headers ,status, request);
    }

    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException exception,
                                                                HttpHeaders headers, HttpStatus status,
                                                                WebRequest request){

        String path = exception.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining());

        ProblemType problemType = ProblemType.CORPO_DO_JSON_ESTA_INCORRETO;
        String detail = String.format("A propriedade '%s' recebeu o valor '%s, que é de um tipo inválido. " +
                "Corrija e informe um valor compatível com o tipo %s.", path, exception.getValue(),
                exception.getTargetType().getSimpleName());

        Problem problem = createWithBuilderProblem(status, problemType, detail)
                .withBuilderUserMessage("O campo não pode ser nulo!");

        return handleExceptionInternal(exception,problem, headers,status,request);
    }

    @ExceptionHandler(EntidadeInexistenteException.class)
    public ResponseEntity<Problem> handleIdNaoEncontradoException(
            EntidadeInexistenteException exception) {
        Problem problem = new Problem()
                .withBuilderTimestamp(LocalDateTime.now())
                .withBuilderUserMessage(exception.getMessage());
        return ResponseEntity.status (HttpStatus.NOT_FOUND)
                .body(problem);
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
                    .withBuilderStatus(status.value())
                    .withBuilderUserMessage("O campo disponivel está com valor inválido!!!");
        } else if (body instanceof  String) {
            body = new Problem()
                    .withBuilderTitle((String) body)
                    .withBuilderStatus(status.value())
                    .withBuilderUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);
        }
        return super.handleExceptionInternal(exception, body, headers, status, request);
    }

    private Problem createWithBuilderProblem(HttpStatus status, ProblemType problemType,
                                             String detail){
        return new Problem()
                .withBuilderStatus(status.value())
                .withBuilderType(problemType.getUri())
                .withBuilderTitle(problemType.getTitle())
                .withBuilderDetail(detail)
                .withBuilderUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL);
    }
}

