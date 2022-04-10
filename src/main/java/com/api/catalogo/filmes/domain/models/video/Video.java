package com.api.catalogo.filmes.domain.models.video;

import lombok.Getter;

import java.util.List;

@Getter
public class Video {

    private int id;
    private List<VideosResult> results;
}
