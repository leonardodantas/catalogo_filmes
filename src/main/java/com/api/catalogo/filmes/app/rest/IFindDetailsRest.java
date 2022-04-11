package com.api.catalogo.filmes.app.rest;

import com.api.catalogo.filmes.domain.details.MovieDetailDTO;

public interface IFindDetailsRest {
    MovieDetailDTO searchDetailsTMDB(String url);
}
