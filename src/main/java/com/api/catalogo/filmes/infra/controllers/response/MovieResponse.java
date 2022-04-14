package com.api.catalogo.filmes.infra.controllers.response;

import com.api.catalogo.filmes.domain.movie.Movie;

import java.util.List;

public record MovieResponse (
        int id,
        boolean adult,
        String backdrop_path,
        List<Integer> genre_ids,
        String original_language,
        String title,
        String original_title,
        String overview,
        double popularity,
        String poster_path,
        String release_date,
        double vote_average,
        int vote_count,
        boolean video
) {
    public static MovieResponse from(Movie movie) {
        return new MovieResponse(movie.getId(),
                movie.isAdult(),
                movie.getBackdrop_path(),
                movie.getGenre_ids(),
                movie.getOriginal_language(),
                movie.getTitle(),
                movie.getOriginal_title(),
                movie.getOverview(),
                movie.getPopularity(),
                movie.getPoster_path(),
                movie.getRelease_date(),
                movie.getVote_average(),
                movie.getVote_count(),
                movie.isVideo());
    }
}