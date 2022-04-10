package com.api.catalogo.filmes.app.usecases;

import com.api.catalogo.filmes.app.utils.tmdb.Language;
import com.api.catalogo.filmes.domain.movie.MovieDTO;
import com.api.catalogo.filmes.domain.pagination.PaginationDTO;

public interface IFindSimilarMovies {

    PaginationDTO<MovieDTO> listSimilarMovies(int movie, int page, Language language);

}
