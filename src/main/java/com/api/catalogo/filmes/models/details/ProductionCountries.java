package com.api.catalogo.filmes.models.details;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductionCountries {

    @JsonProperty("iso_3166_1")
    private String iso;
    @JsonProperty("name")
    private String nome;
}
