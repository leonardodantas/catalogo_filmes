package com.api.catalogo.filmes.domain.models.details;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductionCities {

    @JsonProperty("id")
    private int id;
    @JsonProperty("logo_path")
    private String caminhoDoLogo;
    @JsonProperty("name")
    private String nome;
    @JsonProperty("origin_country")
    private String paisDeOrigem;

}
