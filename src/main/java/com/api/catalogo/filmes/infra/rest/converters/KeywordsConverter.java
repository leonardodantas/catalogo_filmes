package com.api.catalogo.filmes.infra.rest.converters;

import com.api.catalogo.filmes.domain.keyword.Keywords;
import com.api.catalogo.filmes.infra.rest.jsons.KeywordsRest;

public class KeywordsConverter {

    public static Keywords convert(KeywordsRest keywordsRest) {
        return Keywords.builder()
                .id(keywordsRest.id())
                .keywords(keywordsRest.keywords().stream().map(KeywordConverter::convert).toList())
                .build();
    }
}
