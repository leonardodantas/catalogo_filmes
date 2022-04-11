package com.api.catalogo.filmes.app.usecases.impl;

import com.api.catalogo.filmes.app.rest.IFindAllMoviesRest;
import com.api.catalogo.filmes.app.usecases.IFindAll;
import com.api.catalogo.filmes.app.utils.tmdb.Language;
import com.api.catalogo.filmes.app.utils.tmdb.RequestMovie;
import com.api.catalogo.filmes.app.utils.tmdb.UrlTMDBFactory;
import com.api.catalogo.filmes.domain.movie.MovieDTO;
import com.api.catalogo.filmes.domain.pagination.PaginationDTO;
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
    public PaginationDTO<MovieDTO> execute(final RequestMovie requestMovie, final int page, final Language language) {
        final var url = urlTMDBFactory.createURLForSearhMoviePerType(requestMovie, page, language);
        return findAllMoviesRest.searchAllMoviesByCategoryTMDB(url);
    }

}
