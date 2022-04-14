package com.api.catalogo.filmes.infra.controllers.response;

import com.api.catalogo.filmes.domain.keyword.Keyword;

public record KeywordResponse(
        String id,
        String name
) {
    public static KeywordResponse from(Keyword keyword) {
        return new KeywordResponse(
                keyword.getId(),
                keyword.getName());
    }
}
