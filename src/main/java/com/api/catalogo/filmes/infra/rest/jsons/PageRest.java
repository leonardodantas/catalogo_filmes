package com.api.catalogo.filmes.infra.rest.jsons;

import java.util.List;

public record PageRest<T>(
        int page,
        List<T> results,
        int total_pages,
        int total_results
) {
}
