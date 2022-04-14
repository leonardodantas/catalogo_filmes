package com.api.catalogo.filmes.app.rest;

import com.api.catalogo.filmes.domain.details.MovieDetail;

public interface IFindDetailsRest {
    MovieDetail searchDetailsTMDB(String url);
}
