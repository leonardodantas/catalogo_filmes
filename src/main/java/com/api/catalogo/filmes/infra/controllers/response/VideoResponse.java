package com.api.catalogo.filmes.infra.controllers.response;

import com.api.catalogo.filmes.domain.video.Video;
import com.api.catalogo.filmes.domain.video.VideosResult;

import java.util.List;

public record VideoResponse(
        int id,
        List<VideosResultResponse> results
) {
    public static VideoResponse from(Video video) {
        return new VideoResponse(video.getId(), video.getResults().stream().map(VideosResultResponse::from).toList());
    }
}
