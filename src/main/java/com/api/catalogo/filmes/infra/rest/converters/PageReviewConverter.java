package com.api.catalogo.filmes.infra.rest.converters;

import com.api.catalogo.filmes.domain.pagination.Page;
import com.api.catalogo.filmes.domain.review.Review;
import com.api.catalogo.filmes.infra.rest.jsons.PageRest;
import com.api.catalogo.filmes.infra.rest.jsons.ReviewRest;

public class PageReviewConverter {

    public static Page<Review> convert(PageRest<ReviewRest> pageRest) {
        final var reviews = pageRest.results().stream()
                .map(review ->
                   Review.builder()
                           .author(review.author())
                           .author_details(AuthorDetailsConverter.convert(review.author_details()))
                           .content(review.content())
                           .created_at(review.created_at())
                           .updated_at(review.updated_at())
                           .url(review.url())
                           .build()).toList();

        return new Page<>(pageRest.page(), reviews, pageRest.total_pages(), pageRest.total_results());
    }
}
