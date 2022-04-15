package com.api.catalogo.filmes.infra.rest.jsons;

public record ErrorApi(
        boolean success,
        String status_code,
        String status_message) {
}
