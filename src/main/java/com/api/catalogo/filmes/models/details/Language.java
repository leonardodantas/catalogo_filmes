package com.api.catalogo.filmes.models.details;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Language {

    @JsonProperty("english_name")
    private String nomeIngles;
    @JsonProperty("iso_639_1")
    private String iso;
    @JsonProperty("name")
    private String nome;

}
