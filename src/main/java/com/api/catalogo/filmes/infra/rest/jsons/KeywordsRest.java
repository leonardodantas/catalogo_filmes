package com.api.catalogo.filmes.infra.rest.jsons;

import java.util.List;

public record KeywordsRest(
        String id,
        List<KeywordRest> keywords
) {
}
