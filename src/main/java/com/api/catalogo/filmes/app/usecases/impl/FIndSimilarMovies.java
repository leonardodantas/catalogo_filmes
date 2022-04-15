package com.api.catalogo.filmes.app.usecases.impl;

import com.api.catalogo.filmes.app.models.ISimilarMovies;
import com.api.catalogo.filmes.app.rest.IFindSimilarRest;
import com.api.catalogo.filmes.app.usecases.IFindSimilarMovies;
import com.api.catalogo.filmes.domain.movie.Movie;
import com.api.catalogo.filmes.domain.pagination.Page;
import org.springframework.stereotype.Service;

@Service
public class FIndSimilarMovies implements IFindSimilarMovies {

    private final IFindSimilarRest findSimilarRest;

    public FIndSimilarMovies(final IFindSimilarRest findSimilarRest) {
        this.findSimilarRest = findSimilarRest;
    }

    @Override
    public Page<Movie> searchSimilarMovies(final ISimilarMovies similarMovies) {
        return findSimilarRest.searchSimilarMoviesTMDB(similarMovies);
    }

}
