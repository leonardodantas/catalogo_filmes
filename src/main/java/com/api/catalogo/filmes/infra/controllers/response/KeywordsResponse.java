package com.api.catalogo.filmes.infra.controllers.response;

import com.api.catalogo.filmes.domain.keyword.Keywords;

import java.util.List;

public record KeywordsResponse(
        String id,
        List<KeywordResponse> keywords
) {
    public static KeywordsResponse from(final Keywords keywords) {
        return new KeywordsResponse(keywords.getId(), keywords.getKeywords().stream().map(KeywordResponse::from).toList());
    }
}
