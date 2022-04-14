package com.api.catalogo.filmes.infra.rest.converters;

import com.api.catalogo.filmes.domain.details.ProductionCountries;
import com.api.catalogo.filmes.infra.rest.jsons.ProductionCountriesRest;

public class ProductionCountriesConverter {

    public static ProductionCountries convert(ProductionCountriesRest productionCountriesRest){
        return ProductionCountries.builder()
                .iso_3166_1(productionCountriesRest.iso_3166_1())
                .name(productionCountriesRest.name())
                .build();
    }
}
