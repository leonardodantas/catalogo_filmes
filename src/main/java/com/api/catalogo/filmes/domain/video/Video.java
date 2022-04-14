package com.api.catalogo.filmes.domain.video;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Video {
    private int id;
    private List<VideosResult> results;
}
