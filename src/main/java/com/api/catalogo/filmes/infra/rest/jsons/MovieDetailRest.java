package com.api.catalogo.filmes.infra.rest.jsons;

import java.util.List;

public record MovieDetailRest(
        int id,
        boolean adult,
        String backdrop_path,
        double budget,
        List<GenderDetailRest> genres,
        String homepage,
        String imdb_id,
        String original_language,
        String original_title,
        String overview,
        double popularity,
        String poster_path,
        List<ProductionCitiesRest> production_companies,
        List<ProductionCountriesRest> production_countries,
        String release_date,
        double revenue,
        String runtime,
        List<LanguageRest> spoken_languages,
        String status,
        String tagline,
        String title,
        boolean video,
        double vote_average,
        int vote_count
) {
}
