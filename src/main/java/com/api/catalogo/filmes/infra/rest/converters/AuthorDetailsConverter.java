package com.api.catalogo.filmes.infra.rest.converters;

import com.api.catalogo.filmes.domain.review.AuthorDetails;
import com.api.catalogo.filmes.infra.rest.jsons.AuthorDetailsRest;

public class AuthorDetailsConverter {

    public static AuthorDetails convert(AuthorDetailsRest authorDetailsRest){
        return AuthorDetails.builder()
                .avatar_path(authorDetailsRest.avatar_path())
                .name(authorDetailsRest.name())
                .rating(authorDetailsRest.rating())
                .username(authorDetailsRest.username())
                .build();
    }
}
