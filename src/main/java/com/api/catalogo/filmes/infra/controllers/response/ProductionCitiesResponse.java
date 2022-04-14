package com.api.catalogo.filmes.infra.controllers.response;

import com.api.catalogo.filmes.domain.details.ProductionCities;

import java.util.List;

public record ProductionCitiesResponse (
        int id,
        String logo_path,
        String name,
        String origin_country
) {
    public static ProductionCitiesResponse from(ProductionCities production_companies) {
        return new ProductionCitiesResponse( production_companies.getId(),
                production_companies.getLogo_path(),
                production_companies.getName(),
                production_companies.getOrigin_country());
    }
}
