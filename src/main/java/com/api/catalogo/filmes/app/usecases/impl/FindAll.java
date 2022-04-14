package com.api.catalogo.filmes.app.usecases.impl;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.app.models.ITypeMovie;
import com.api.catalogo.filmes.app.rest.IFindAllMoviesRest;
import com.api.catalogo.filmes.app.usecases.IFindAll;
import com.api.catalogo.filmes.app.utils.tmdb.UrlTMDBFactory;
import com.api.catalogo.filmes.domain.movie.Movie;
import com.api.catalogo.filmes.domain.pagination.Page;
import org.springframework.stereotype.Service;

@Service
public class FindAll implements IFindAll {

    private final UrlTMDBFactory urlTMDBFactory;
    private final IFindAllMoviesRest findAllMoviesRest;

    public FindAll(final UrlTMDBFactory urlTMDBFactory, final IFindAllMoviesRest findAllMoviesRest) {
        this.urlTMDBFactory = urlTMDBFactory;
        this.findAllMoviesRest = findAllMoviesRest;
    }

    @Override
    public Page<Movie> execute(final ITypeMovie typeMovie, final ILanguageMovie languageMovie, final int page) {
        final var url = urlTMDBFactory.createURLForSearhMoviePerType(typeMovie.getType(), page, languageMovie.getLanguage());
        return findAllMoviesRest.searchAllMoviesByCategoryTMDB(url);
    }
}
