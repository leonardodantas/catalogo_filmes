package com.api.catalogo.filmes.domain.review;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Review {
    private String author;
    private AuthorDetails author_details;
    private String content;
    private String created_at;
    private String updated_at;
    private String url;
}
