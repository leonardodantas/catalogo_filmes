package com.api.catalogo.filmes.app.rest;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.domain.movie.Movie;
import com.api.catalogo.filmes.domain.pagination.Page;

public interface IFindSimilarRest {
    Page<Movie> searchSimilarMoviesTMDB(final int movieId, final int page, final ILanguageMovie language);
}
