package com.api.catalogo.filmes.infra.rest.converters;

import com.api.catalogo.filmes.domain.details.Language;
import com.api.catalogo.filmes.infra.rest.jsons.LanguageRest;

public class LanguageConverter {

    public static Language convert(LanguageRest languageRest) {
        return Language.builder()
                .english_name(languageRest.english_name())
                .iso_639_1(languageRest.iso_639_1())
                .name(languageRest.name())
                .build();
    }
}
