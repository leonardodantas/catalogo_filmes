package com.api.catalogo.filmes.app.utils.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<Object> handleAnyExcepton(ResponseStatusException error){

        ErrorResponse errorResponse = new ErrorResponse(error);
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), error.getStatus());
    }
}
