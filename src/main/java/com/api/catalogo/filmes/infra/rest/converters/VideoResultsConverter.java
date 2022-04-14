package com.api.catalogo.filmes.infra.rest.converters;

import com.api.catalogo.filmes.domain.video.VideosResult;
import com.api.catalogo.filmes.domain.video.VideosResultRest;

public class VideoResultsConverter {

    public static VideosResult convert(VideosResultRest videosResultRest) {
        return VideosResult.builder()
                .id(videosResultRest.id())
                .iso_639_1(videosResultRest.iso_639_1())
                .iso_3166_1(videosResultRest.iso_3166_1())
                .key(videosResultRest.key())
                .name(videosResultRest.name())
                .site(videosResultRest.site())
                .size(videosResultRest.size())
                .type(videosResultRest.type())
                .urlYoutube(GenerateURL.getURLYoutube(videosResultRest.site(), videosResultRest.key()))
                .build();
    }
}
