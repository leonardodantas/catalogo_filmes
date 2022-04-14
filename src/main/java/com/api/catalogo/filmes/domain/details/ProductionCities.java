package com.api.catalogo.filmes.domain.details;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductionCities {

    private int id;
    private String logo_path;
    private String name;
    private String origin_country;
}
