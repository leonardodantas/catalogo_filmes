package com.api.catalogo.filmes.infra.controllers.response;

import com.api.catalogo.filmes.domain.review.AuthorDetails;

public record AuthorDetailsResponse(
        String name,
        String username,
        String avatar_path,
        String rating
) {
    public static AuthorDetailsResponse from(AuthorDetails authorDetails) {
        return new AuthorDetailsResponse(
                authorDetails.getName(),
                authorDetails.getUsername(),
                authorDetails.getAvatar_path(),
                authorDetails.getRating()
        );
    }
}
