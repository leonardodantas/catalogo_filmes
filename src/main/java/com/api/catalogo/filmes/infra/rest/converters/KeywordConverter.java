package com.api.catalogo.filmes.infra.rest.converters;

import com.api.catalogo.filmes.domain.keyword.Keyword;
import com.api.catalogo.filmes.infra.rest.jsons.KeywordRest;

public class KeywordConverter {

    public static Keyword convert(KeywordRest keywordRest) {
        return Keyword.builder()
                .id(keywordRest.id())
                .name(keywordRest.name())
                .build();
    }
}
