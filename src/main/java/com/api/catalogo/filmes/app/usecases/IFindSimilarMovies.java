package com.api.catalogo.filmes.app.usecases;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.infra.controllers.request.LanguageMovieRequest;
import com.api.catalogo.filmes.domain.movie.Movie;
import com.api.catalogo.filmes.domain.pagination.Page;

public interface IFindSimilarMovies {

    Page<Movie> searchSimilarMovies(int movie, int page, ILanguageMovie language);

}
