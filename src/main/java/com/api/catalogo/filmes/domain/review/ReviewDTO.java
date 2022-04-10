package com.api.catalogo.filmes.domain.review;

import com.api.catalogo.filmes.domain.pagination.IPagination;

public class ReviewDTO implements IPagination {
    private String author;
    private AuthorDetails author_details;
    private String content;
    private String created_at;
    private String updated_at;
    private String url;
}
