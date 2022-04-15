package com.api.catalogo.filmes.app.usecases;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.domain.details.MovieDetail;

public interface IFindDetails {

    MovieDetail execute(final int movieId, final ILanguageMovie language, final String apiKey);

}
