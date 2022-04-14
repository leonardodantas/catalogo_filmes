package com.api.catalogo.filmes.infra.rest.jsons;

public record ProductionCitiesRest (
        int id,
        String logo_path,
        String name,
        String origin_country
){
}
