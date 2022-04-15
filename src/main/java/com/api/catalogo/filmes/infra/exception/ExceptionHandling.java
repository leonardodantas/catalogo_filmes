package com.api.catalogo.filmes.infra.exception;

import com.api.catalogo.filmes.infra.rest.jsons.ErrorApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class ExceptionHandling {

    private final ObjectMapper objectMapper;

    public ExceptionHandling(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getMessage(final String message) {
        try {
            final var errorApi = objectMapper.readValue(message, ErrorApi.class);
            return errorApi.status_message();
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
