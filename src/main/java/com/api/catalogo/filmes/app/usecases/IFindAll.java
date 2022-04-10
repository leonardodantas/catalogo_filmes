package com.api.catalogo.filmes.app.usecases;

import com.api.catalogo.filmes.app.utils.tmdb.Language;
import com.api.catalogo.filmes.app.utils.tmdb.RequestMovie;
import com.api.catalogo.filmes.domain.models.movie.MovieDTO;
import com.api.catalogo.filmes.domain.models.pagination.PaginationDTO;

public interface IFindAll {

    PaginationDTO<MovieDTO> execute(RequestMovie requestMovie, int page, Language language);
}
