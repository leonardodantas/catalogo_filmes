package com.api.catalogo.filmes.app.rest;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.domain.details.MovieDetail;

public interface IFindDetailsRest {
    MovieDetail searchDetailsTMDB(final int movieId, final ILanguageMovie language);
}
