package com.api.catalogo.filmes.app.usecases;

import com.api.catalogo.filmes.app.models.ISimilarMovies;
import com.api.catalogo.filmes.domain.movie.Movie;
import com.api.catalogo.filmes.domain.pagination.Page;

public interface IFindSimilarMovies {

    Page<Movie> searchSimilarMovies(final ISimilarMovies similarMovies);

}
