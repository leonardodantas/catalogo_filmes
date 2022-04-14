package com.api.catalogo.filmes.app.usecases.impl;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.app.rest.IFindSimilarRest;
import com.api.catalogo.filmes.app.usecases.IFindSimilarMovies;
import com.api.catalogo.filmes.app.utils.tmdb.UrlTMDBFactory;
import com.api.catalogo.filmes.domain.movie.Movie;
import com.api.catalogo.filmes.domain.pagination.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FIndSimilarMovies implements IFindSimilarMovies {

    private final UrlTMDBFactory urlTMDBFactory;
    private final IFindSimilarRest findSimilarRest;

    public FIndSimilarMovies(final UrlTMDBFactory urlTMDBFactory, final IFindSimilarRest findSimilarRest) {
        this.findSimilarRest = findSimilarRest;
        this.urlTMDBFactory = urlTMDBFactory;
    }

    @Override
    public Page<Movie> searchSimilarMovies(final int movie, final int page, final ILanguageMovie language) {
        final var url = urlTMDBFactory.createURLForListSimilarMovies(movie, page, language.getLanguage());
        return findSimilarRest.searchSimilarMoviesTMDB(url);
    }

}
