package com.api.catalogo.filmes.domain.details;

import lombok.Getter;

@Getter
public class ProductionCities {

    private final String URL_BASE = "https://www.themoviedb.org/t/p/w220_and_h330_face";

    private int id;
    private String logo_path;
    private String name;
    private String origin_country;

    public String getLogo_path() {
        return URL_BASE.concat(logo_path);
    }
}
