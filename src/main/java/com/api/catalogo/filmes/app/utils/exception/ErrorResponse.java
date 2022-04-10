package com.api.catalogo.filmes.app.utils.exception;

import lombok.Getter;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class ErrorResponse {

    private final String uuid;
    private final String message;
    private final LocalDateTime date;

    public ErrorResponse(ResponseStatusException error) {
        uuid = UUID.randomUUID().toString();
        message = error.getReason();
        date = LocalDateTime.now();
    }
}
