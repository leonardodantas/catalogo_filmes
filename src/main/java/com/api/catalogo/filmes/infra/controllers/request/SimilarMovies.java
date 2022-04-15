package com.api.catalogo.filmes.infra.controllers.request;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.app.models.ISimilarMovies;

public class SimilarMovies implements ISimilarMovies {

    private final int movieId;
    private final int page;
    private final ILanguageMovie languageMovie;
    private final String apiKey;

    private SimilarMovies(int movieId, int page, ILanguageMovie languageMovie, String apiKey) {
        this.movieId = movieId;
        this.page = page;
        this.languageMovie = languageMovie;
        this.apiKey = apiKey;
    }

    public static SimilarMovies of(int movieId, int page, ILanguageMovie languageMovie, String apiKey) {
        return new SimilarMovies(movieId, page, languageMovie, apiKey);
    }

    @Override
    public int getMovieId() {
        return movieId;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public ILanguageMovie getLanguage() {
        return languageMovie;
    }

    @Override
    public String getApiKey() {
        return apiKey;
    }
}
