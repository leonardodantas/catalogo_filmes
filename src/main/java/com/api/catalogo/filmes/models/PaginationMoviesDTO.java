package com.api.catalogo.filmes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class PaginationMoviesDTO {

    @JsonProperty(value = "page")
    private int pagina;

    @JsonProperty(value = "results")
    private List<FilmesDTO> resultados;

    @JsonProperty(value = "total_pages")
    private int totalDePaginas;

    @JsonProperty(value = "total_results")
    private int totalDeResultados;
}
