package com.api.catalogo.filmes.infra.controllers.response;

import com.api.catalogo.filmes.domain.video.VideosResult;

public record VideosResultResponse(
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
    public static VideosResultResponse from(VideosResult videosResult) {
        return new VideosResultResponse(
                videosResult.getId(),
                videosResult.getIso_639_1(),
                videosResult.getIso_3166_1(),
                videosResult.getKey(),
                videosResult.getName(),
                videosResult.getSite(),
                videosResult.getSize(),
                videosResult.getType(),
                videosResult.getUrlYoutube());
    }
}
