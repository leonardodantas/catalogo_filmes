package com.api.catalogo.filmes.domain.models.pagination;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class PaginationDTO<T extends IPagination> {

    @JsonProperty(value = "page")
    private int pagina;

    @JsonProperty(value = "results")
    private List<T> resultados;

    @JsonProperty(value = "total_pages")
    private int totalDePaginas;

    @JsonProperty(value = "total_results")
    private int totalDeResultados;
}
