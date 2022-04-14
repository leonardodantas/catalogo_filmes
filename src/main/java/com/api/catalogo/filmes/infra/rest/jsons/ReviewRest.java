package com.api.catalogo.filmes.infra.rest.jsons;


public record ReviewRest(
        String author,
        AuthorDetailsRest author_details,
        String content,
        String created_at,
        String updated_at,
        String url) {
}
