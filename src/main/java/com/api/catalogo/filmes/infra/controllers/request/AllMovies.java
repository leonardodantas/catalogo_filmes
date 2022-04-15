package com.api.catalogo.filmes.infra.controllers.request;

import com.api.catalogo.filmes.app.models.IAllMovies;
import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.app.models.ITypeMovie;

public class AllMovies implements IAllMovies {

    private final ITypeMovie typeMovie;
    private final ILanguageMovie languageMovie;
    private final int page;
    private final String apiKey;

    private AllMovies(ITypeMovie typeMovie, ILanguageMovie languageMovie, int page, String apiKey) {
        this.typeMovie = typeMovie;
        this.languageMovie = languageMovie;
        this.page = page;
        this.apiKey = apiKey;
    }

    public static AllMovies of(ITypeMovie typeMovie, ILanguageMovie languageMovie, int page, String apiKey) {
        return new AllMovies(typeMovie, languageMovie, page, apiKey);
    }

    @Override
    public ITypeMovie getTypeMovie() {
        return typeMovie;
    }

    @Override
    public ILanguageMovie getLanguageMovie() {
        return languageMovie;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public String getApiKey() {
        return apiKey;
    }

}
