package com.api.catalogo.filmes.app.usecases.impl;

import com.api.catalogo.filmes.app.rest.IFindDetailsRest;
import com.api.catalogo.filmes.app.usecases.IFindDetails;
import com.api.catalogo.filmes.app.utils.tmdb.Language;
import com.api.catalogo.filmes.app.utils.tmdb.UrlTMDBFactory;
import com.api.catalogo.filmes.domain.details.MovieDetailDTO;
import org.springframework.stereotype.Service;

@Service
public class FindDetails implements IFindDetails {

    private final IFindDetailsRest findDetailsRest;
    private final UrlTMDBFactory urlTMDBFactory;

    public FindDetails(final UrlTMDBFactory urlTMDBFactory, final IFindDetailsRest findDetailsRest) {
        this.findDetailsRest = findDetailsRest;
        this.urlTMDBFactory = urlTMDBFactory;
    }

    @Override
    public MovieDetailDTO execute(final int movie, final Language language) {
        final var url = urlTMDBFactory.criarUrlParaBuscarDetalhesDoFilmes(movie, language);
        return findDetailsRest.searchDetailsTMDB(url);
    }

}
