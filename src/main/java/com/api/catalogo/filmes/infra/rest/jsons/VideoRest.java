package com.api.catalogo.filmes.infra.rest.jsons;

import com.api.catalogo.filmes.domain.video.VideosResultRest;

import java.util.List;

public record VideoRest(
        int id,
        List<VideosResultRest> results
) {
}
