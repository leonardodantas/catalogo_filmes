package com.api.catalogo.filmes.domain.video;

import lombok.Getter;

import java.util.List;

@Getter
public class Video {

    private int id;
    private List<VideosResult> results;
}
