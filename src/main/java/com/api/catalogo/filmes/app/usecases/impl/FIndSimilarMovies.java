package com.api.catalogo.filmes.app.usecases.impl;

import com.api.catalogo.filmes.app.rest.IFindSimilarRest;
import com.api.catalogo.filmes.app.usecases.IFindSimilarMovies;
import com.api.catalogo.filmes.app.utils.tmdb.Language;
import com.api.catalogo.filmes.app.utils.tmdb.UrlTMDBFactory;
import com.api.catalogo.filmes.domain.movie.MovieDTO;
import com.api.catalogo.filmes.domain.pagination.PaginationDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FIndSimilarMovies implements IFindSimilarMovies {

    private final UrlTMDBFactory urlTMDBFactory;
    private final IFindSimilarRest findSimilarRest;

    public FIndSimilarMovies(final RestTemplate restTemplate, final UrlTMDBFactory urlTMDBFactory, final IFindSimilarRest findSimilarRest) {
        this.findSimilarRest = findSimilarRest;
        this.urlTMDBFactory = urlTMDBFactory;
    }

    @Override
    public PaginationDTO<MovieDTO> listSimilarMovies(final int movie, final int page, final Language language) {
        final var url = urlTMDBFactory.createURLForListSimilarMovies(movie, page, language);
        return findSimilarRest.listSimilarMoviesTMDB(url);
    }

}
