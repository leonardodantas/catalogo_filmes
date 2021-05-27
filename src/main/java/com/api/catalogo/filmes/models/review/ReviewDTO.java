package com.api.catalogo.filmes.models.review;

import com.api.catalogo.filmes.models.pagination.IPagination;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewDTO implements IPagination {

    @JsonProperty(value = "author")
    private String autor;

    @JsonProperty(value = "author_details")
    private AuthorDetails authorDetails;

    @JsonProperty(value = "content")
    private String content;

    @JsonProperty(value = "created_at")
    private String dataCriacao;

    @JsonProperty(value = "updated_at")
    private String dataAtualizacao;

    @JsonProperty(value = "url")
    private String urlOriginal;

}
