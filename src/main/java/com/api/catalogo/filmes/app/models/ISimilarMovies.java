package com.api.catalogo.filmes.app.models;

public interface ISimilarMovies {
    int getMovieId();
    int getPage();
    ILanguageMovie getLanguage();
    String getApiKey();
}
