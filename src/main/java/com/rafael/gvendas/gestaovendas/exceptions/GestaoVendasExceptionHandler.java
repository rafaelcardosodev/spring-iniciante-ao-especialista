package com.rafael.gvendas.gestaovendas.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GestaoVendasExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String CONST_VALIDATION_NOT_BLANK = "NotBlank";
    public static final String CONST_VALIDATION_LENGTH = "Length";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Error> errors = generateErrorList(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    private List<Error> generateErrorList(BindingResult bindingResult) {
        List<Error> errors = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(error -> {
            String userMessage = handleUserErrorMessage(error);
            String developerMessage = error.toString();
            errors.add(new Error(userMessage, developerMessage));
        });
        return errors;
    }

    private String handleUserErrorMessage(FieldError error) {
        if (error.getCode().equals(CONST_VALIDATION_NOT_BLANK)) {
            return error.getDefaultMessage().concat(" é obrigatório");
        }

        if (error.getCode().equals(CONST_VALIDATION_LENGTH)) {
            return error.getDefaultMessage().concat(String.format(" deve ter entre %s e %s caracteres", error.getArguments()[2], error.getArguments()[1]));
        }
        return error.toString();
    }


}
