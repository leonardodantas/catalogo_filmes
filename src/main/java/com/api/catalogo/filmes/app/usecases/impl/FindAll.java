package com.api.catalogo.filmes.app.usecases.impl;

import com.api.catalogo.filmes.app.models.IAllMovies;
import com.api.catalogo.filmes.app.rest.IFindAllMoviesRest;
import com.api.catalogo.filmes.app.usecases.IFindAll;
import com.api.catalogo.filmes.domain.movie.Movie;
import com.api.catalogo.filmes.domain.pagination.Page;
import org.springframework.stereotype.Service;

@Service
public class FindAll implements IFindAll {

    private final IFindAllMoviesRest findAllMoviesRest;

    public FindAll(final IFindAllMoviesRest findAllMoviesRest) {
        this.findAllMoviesRest = findAllMoviesRest;
    }

    @Override
    public Page<Movie> execute(final IAllMovies allMovies) {
        return findAllMoviesRest.searchAllMoviesByCategoryTMDB(allMovies);
    }

}
