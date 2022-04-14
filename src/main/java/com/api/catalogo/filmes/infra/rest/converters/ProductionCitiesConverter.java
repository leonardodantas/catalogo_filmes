package com.api.catalogo.filmes.infra.rest.converters;

import com.api.catalogo.filmes.domain.details.ProductionCities;
import com.api.catalogo.filmes.infra.rest.jsons.ProductionCitiesRest;

public class ProductionCitiesConverter {

    public static ProductionCities convert(ProductionCitiesRest productionCitiesRest){
        return ProductionCities.builder()
                .id(productionCitiesRest.id())
                .name(productionCitiesRest.name())
                .logo_path(GenerateURL.getURLTMDB(productionCitiesRest.logo_path()))
                .origin_country(productionCitiesRest.origin_country())
                .build();
    }
}
