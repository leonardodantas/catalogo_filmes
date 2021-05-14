package com.api.catalogo.filmes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenerosDetalhe {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String nome;
}
