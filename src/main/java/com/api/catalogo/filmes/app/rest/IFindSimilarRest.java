package com.api.catalogo.filmes.app.rest;

import com.api.catalogo.filmes.domain.movie.MovieDTO;
import com.api.catalogo.filmes.domain.pagination.PaginationDTO;

public interface IFindSimilarRest {
    PaginationDTO<MovieDTO> listSimilarMoviesTMDB(String url);
}
