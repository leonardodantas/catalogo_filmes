package com.api.catalogo.filmes.app.rest;

import com.api.catalogo.filmes.app.models.IAllMovies;
import com.api.catalogo.filmes.domain.movie.Movie;
import com.api.catalogo.filmes.domain.pagination.Page;
import com.api.catalogo.filmes.infra.controllers.request.AllMovies;

public interface IFindAllMoviesRest {
    Page<Movie> searchAllMoviesByCategoryTMDB(final IAllMovies allMovies);
}
