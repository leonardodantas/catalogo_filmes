package com.api.catalogo.filmes.infra.controllers.response;

import com.api.catalogo.filmes.domain.review.Review;

public record ReviewResponse(
         String author,
         AuthorDetailsResponse author_details,
         String content,
         String created_at,
         String updated_at,
         String url
) {
    public static ReviewResponse from(Review review) {
        return new ReviewResponse(
                review.getAuthor(),
                AuthorDetailsResponse.from(review.getAuthor_details()),
                review.getContent(),
                review.getCreated_at(),
                review.getUpdated_at(),
                review.getUrl()
        );
    }
}
