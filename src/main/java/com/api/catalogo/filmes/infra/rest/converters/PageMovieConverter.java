package com.api.catalogo.filmes.infra.rest.converters;

import com.api.catalogo.filmes.domain.movie.Movie;
import com.api.catalogo.filmes.domain.pagination.Page;
import com.api.catalogo.filmes.infra.rest.jsons.MovieRest;
import com.api.catalogo.filmes.infra.rest.jsons.PageRest;

public class PageMovieConverter {

    public static Page<Movie> convert(final PageRest<MovieRest> pageRest) {

        final var movies = pageRest.results().stream().map(movieRest -> Movie.builder()
                .id(movieRest.id())
                .adult(movieRest.adult())
                .genre_ids(movieRest.genre_ids())
                .original_language(movieRest.original_language())
                .original_title(movieRest.original_title())
                .overview(movieRest.overview())
                .popularity(movieRest.popularity())
                .release_date(movieRest.release_date())
                .title(movieRest.title())
                .video(movieRest.video())
                .vote_average(movieRest.vote_average())
                .vote_count(movieRest.vote_count())
                .backdrop_path(GenerateURL.getURLTMDB(movieRest.backdrop_path()))
                .poster_path(GenerateURL.getURLTMDB(movieRest.poster_path()))
                .build()).toList();

        return new Page<>(pageRest.page(), movies, pageRest.total_pages(), pageRest.total_results());
    }


}
