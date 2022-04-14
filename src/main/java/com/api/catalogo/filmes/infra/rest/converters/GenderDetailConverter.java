package com.api.catalogo.filmes.infra.rest.converters;

import com.api.catalogo.filmes.domain.details.GenderDetail;
import com.api.catalogo.filmes.infra.rest.jsons.GenderDetailRest;

public class GenderDetailConverter {

    public static GenderDetail convert(GenderDetailRest genderDetailRest){
        return GenderDetail.builder()
                .id(genderDetailRest.id())
                .name(genderDetailRest.name())
                .build();
    }
}
