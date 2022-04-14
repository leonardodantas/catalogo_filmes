package com.api.catalogo.filmes.infra.rest.jsons;

public record AuthorDetailsRest(
        String name,
        String username,
        String avatar_path,
        String rating
) {
}
