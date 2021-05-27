package com.api.catalogo.filmes.models.video;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideosResult {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "iso_639_1")
    private String language;

    @JsonProperty(value = "iso_3166_1")
    private String location;

    @JsonProperty(value = "key")
    private String key;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "site")
    private String site;

    @JsonProperty(value = "size")
    private String tamanho;

    @JsonProperty(value = "type")
    private String tipo;

    @Setter
    private String urlYoutube;
}
