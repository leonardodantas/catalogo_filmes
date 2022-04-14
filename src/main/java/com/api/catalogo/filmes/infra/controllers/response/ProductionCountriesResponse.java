package com.api.catalogo.filmes.infra.controllers.response;

import com.api.catalogo.filmes.domain.details.ProductionCountries;

import java.util.List;

public record ProductionCountriesResponse(
        String iso_3166_1,
        String name
) {
    public static ProductionCountriesResponse from(ProductionCountries productionCountries) {
        return new ProductionCountriesResponse(
                productionCountries.getIso_3166_1(),
                productionCountries.getName()
        );
    }
}
