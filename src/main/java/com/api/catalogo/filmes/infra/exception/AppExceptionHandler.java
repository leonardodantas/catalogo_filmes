package com.api.catalogo.filmes.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<Object> handleAnyExcepton(final ResponseStatusException error){
        final var errorResponse = ErrorResponse.from(error);
        return new ResponseEntity<>(errorResponse, error.getStatus());
    }
}
