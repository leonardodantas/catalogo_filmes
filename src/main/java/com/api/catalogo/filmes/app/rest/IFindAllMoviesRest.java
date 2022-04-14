package com.api.catalogo.filmes.app.rest;

import com.api.catalogo.filmes.domain.movie.Movie;
import com.api.catalogo.filmes.domain.pagination.Page;

public interface IFindAllMoviesRest {
    Page<Movie> searchAllMoviesByCategoryTMDB(String url);
}
