package com.api.catalogo.filmes.infra.controllers.request;

import com.api.catalogo.filmes.app.models.ILanguageMovie;

public enum LanguageMovieRequest implements ILanguageMovie {

    PT("pt-BR"),EN("en-EN"),ES("es-ES");

    private final String language;

    LanguageMovieRequest(final String language){
        this.language = language;
    }

    @Override
    public String getLanguage() {
        return language;
    }
}
