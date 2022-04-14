package com.api.catalogo.filmes.domain.details;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProductionCountries {
    private String iso_3166_1;
    private String name;
}
