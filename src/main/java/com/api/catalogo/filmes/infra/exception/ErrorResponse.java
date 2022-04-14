package com.api.catalogo.filmes.infra.exception;

import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

public record ErrorResponse(
        String uuid,
        String message,
        LocalDateTime date
) {
    public static ErrorResponse from(final ResponseStatusException error) {
        return new ErrorResponse(UUID.randomUUID().toString(), error.getMessage(), LocalDateTime.now());
    }
}
