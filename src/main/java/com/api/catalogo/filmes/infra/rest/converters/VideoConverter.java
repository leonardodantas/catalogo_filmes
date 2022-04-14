package com.api.catalogo.filmes.infra.rest.converters;

import com.api.catalogo.filmes.domain.video.Video;
import com.api.catalogo.filmes.infra.rest.jsons.VideoRest;

public class VideoConverter {
    public static Video convert(VideoRest response) {
        return Video.builder()
                .id(response.id())
                .results(response.results().stream().map(VideoResultsConverter::convert).toList())
                .build();
    }
}
