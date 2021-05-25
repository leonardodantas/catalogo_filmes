package com.api.catalogo.filmes.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaisesDeProducao {

    @JsonProperty("iso_3166_1")
    private String iso;
    @JsonProperty("name")
    private String nome;
}
