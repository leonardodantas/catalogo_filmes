package com.api.catalogo.filmes.infra.controllers.response;

import com.api.catalogo.filmes.domain.details.Language;

public record LanguageResponse(String english_name,
                               String iso_639_1,
                               String name) {

    public static LanguageResponse from(Language language) {
        return new LanguageResponse(language.getEnglish_name(),
                language.getIso_639_1(),
                language.getName()
        );
    }
}
