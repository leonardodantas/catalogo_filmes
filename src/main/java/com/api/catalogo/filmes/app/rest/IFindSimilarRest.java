package com.api.catalogo.filmes.app.rest;

import com.api.catalogo.filmes.domain.movie.Movie;
import com.api.catalogo.filmes.domain.pagination.Page;

public interface IFindSimilarRest {
    Page<Movie> searchSimilarMoviesTMDB(String url);
}
