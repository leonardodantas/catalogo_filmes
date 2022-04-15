package com.api.catalogo.filmes.app.rest;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.app.models.ITypeMovie;
import com.api.catalogo.filmes.domain.movie.Movie;
import com.api.catalogo.filmes.domain.pagination.Page;

public interface IFindAllMoviesRest {
    Page<Movie> searchAllMoviesByCategoryTMDB(final ITypeMovie typeMovie, final ILanguageMovie languageMovie, final int page);
}
