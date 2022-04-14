package com.api.catalogo.filmes.infra.controllers.response;

import java.util.List;

public record PageResponse<T>(
        int page,
        List<T> results,
        int total_pages,
        int total_results
) {
}
