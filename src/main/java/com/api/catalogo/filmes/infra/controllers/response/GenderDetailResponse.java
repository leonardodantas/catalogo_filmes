package com.api.catalogo.filmes.infra.controllers.response;

import com.api.catalogo.filmes.domain.details.GenderDetail;

import java.util.List;

public record GenderDetailResponse(
        String id,
        String name) {
    public static GenderDetailResponse from(GenderDetail genres) {
        return new GenderDetailResponse(genres.getId(), genres.getName());
    }
}
