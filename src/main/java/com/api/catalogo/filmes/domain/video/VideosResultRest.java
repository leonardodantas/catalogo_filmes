package com.api.catalogo.filmes.domain.video;


public record VideosResultRest(
        String id,
        String iso_639_1,
        String iso_3166_1,
        String key,
        String name,
        String site,
        String size,
        String type,
        String urlYoutube
) {
}
