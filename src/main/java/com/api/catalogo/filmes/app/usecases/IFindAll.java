package com.api.catalogo.filmes.app.usecases;

import com.api.catalogo.filmes.app.models.IAllMovies;
import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.app.models.ITypeMovie;
import com.api.catalogo.filmes.domain.movie.Movie;
import com.api.catalogo.filmes.domain.pagination.Page;

public interface IFindAll {

    Page<Movie> execute(final IAllMovies allMovies);
}
