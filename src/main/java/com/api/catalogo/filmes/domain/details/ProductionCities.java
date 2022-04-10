package com.api.catalogo.filmes.domain.details;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ProductionCities {
    private int id;
    @Setter
    private String logo_path;
    private String name;
    private String origin_country;

}
