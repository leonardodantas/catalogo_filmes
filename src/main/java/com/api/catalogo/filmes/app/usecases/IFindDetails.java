package com.api.catalogo.filmes.app.usecases;

import com.api.catalogo.filmes.app.utils.tmdb.Language;
import com.api.catalogo.filmes.domain.details.MovieDetailDTO;

public interface IFindDetails {

    MovieDetailDTO execute(int movie, Language language);

}
