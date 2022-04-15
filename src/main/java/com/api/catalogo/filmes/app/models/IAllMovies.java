package com.api.catalogo.filmes.app.models;

public interface IAllMovies {

    ITypeMovie getTypeMovie();
    ILanguageMovie getLanguageMovie();
    int getPage();
    String getApiKey();

}
