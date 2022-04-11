package com.api.catalogo.filmes.domain.video;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideosResult {

    private final String URL_VIDEO = "https://www.youtube.com/watch?v=";

    private String id;
    private String iso_639_1;
    private String iso_3166_1;
    private String key;
    private String name;
    private String site;
    private String size;
    private String type;
    @Setter
    private String urlYoutube;

    public String getUrlYoutube() {
        return type.equals("YouTube") ? URL_VIDEO.concat(this.key) : "";
    }
}
