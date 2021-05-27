package com.api.catalogo.filmes.models.details;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenderDetail {

    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String nome;
}
