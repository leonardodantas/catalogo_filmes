package com.api.catalogo.filmes.infra.rest.jsons;

import java.util.List;

public record VideoRest(
        int id,
        List<VideosResultRest> results
) {
}
