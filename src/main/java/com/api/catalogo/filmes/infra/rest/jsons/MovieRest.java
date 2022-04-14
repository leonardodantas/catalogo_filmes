package com.api.catalogo.filmes.infra.rest.jsons;

import java.util.List;

public record MovieRest(
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

}
