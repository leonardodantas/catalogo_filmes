package com.api.catalogo.filmes.app.usecases.impl;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.app.rest.IFindDetailsRest;
import com.api.catalogo.filmes.app.usecases.IFindDetails;
import com.api.catalogo.filmes.app.utils.tmdb.UrlTMDBFactory;
import com.api.catalogo.filmes.domain.details.MovieDetail;
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
    public MovieDetail execute(final int movieId, final ILanguageMovie language) {
        final var url = urlTMDBFactory.criarUrlParaBuscarDetalhesDoFilmes(movieId, language.getLanguage());
        return findDetailsRest.searchDetailsTMDB(url);
    }

}
