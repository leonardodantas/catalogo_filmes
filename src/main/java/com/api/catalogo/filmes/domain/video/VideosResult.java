package com.api.catalogo.filmes.domain.video;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VideosResult {

    private String id;
    private String iso_639_1;
    private String iso_3166_1;
    private String key;
    private String name;
    private String site;
    private String size;
    private String type;
    private String urlYoutube;

}
