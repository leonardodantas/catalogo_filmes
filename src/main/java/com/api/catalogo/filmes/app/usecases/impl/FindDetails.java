package com.api.catalogo.filmes.app.usecases.impl;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.app.rest.IFindDetailsRest;
import com.api.catalogo.filmes.app.usecases.IFindDetails;
import com.api.catalogo.filmes.domain.details.MovieDetail;
import org.springframework.stereotype.Service;

@Service
public class FindDetails implements IFindDetails {

    private final IFindDetailsRest findDetailsRest;

    public FindDetails(final IFindDetailsRest findDetailsRest) {
        this.findDetailsRest = findDetailsRest;
    }

    @Override
    public MovieDetail execute(final int movieId, final ILanguageMovie language) {
        return findDetailsRest.searchDetailsTMDB(movieId,language);
    }

}
