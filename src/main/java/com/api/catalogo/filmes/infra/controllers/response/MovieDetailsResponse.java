package com.api.catalogo.filmes.infra.controllers.response;

import com.api.catalogo.filmes.domain.details.MovieDetail;

import java.util.List;

public record MovieDetailsResponse(
        int id,
        boolean adult,
        String backdrop_path,
        double budget,
        List<GenderDetailResponse> genres,
        String homepage,
        String imdb_id,
        String original_language,
        String original_title,
        String overview,
        double popularity,
        String poster_path,
        List<ProductionCitiesResponse> production_companies,
        List<ProductionCountriesResponse> production_countries,
        String release_date,
        double revenue,
        String runtime,
        List<LanguageResponse> spoken_languages,
        String status,
        String tagline,
        String title,
        boolean video,
        double vote_average,
        int vote_count
) {
    public static MovieDetailsResponse from(MovieDetail movieDetail) {
        return new MovieDetailsResponse(
                movieDetail.getId(),
                movieDetail.isAdult(),
                movieDetail.getBackdrop_path(),
                movieDetail.getBudget(),
                movieDetail.getGenres().stream().map(GenderDetailResponse::from).toList(),
                movieDetail.getHomepage(),
                movieDetail.getImdb_id(),
                movieDetail.getOriginal_language(),
                movieDetail.getOriginal_title(),
                movieDetail.getOverview(),
                movieDetail.getPopularity(),
                movieDetail.getPoster_path(),
                movieDetail.getProduction_companies().stream().map(ProductionCitiesResponse::from).toList(),
                movieDetail.getProduction_countries().stream().map(ProductionCountriesResponse::from).toList(),
                movieDetail.getRelease_date(),
                movieDetail.getRevenue(),
                movieDetail.getRuntime(),
                movieDetail.getSpoken_languages().stream().map(LanguageResponse::from).toList(),
                movieDetail.getStatus(),
                movieDetail.getTagline(),
                movieDetail.getTitle(),
                movieDetail.isVideo(),
                movieDetail.getVote_average(),
                movieDetail.getVote_count()
        );
    }
}
