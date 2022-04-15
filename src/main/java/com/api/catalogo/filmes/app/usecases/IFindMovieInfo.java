package com.api.catalogo.filmes.app.usecases;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.infra.controllers.request.LanguageMovieRequest;
import com.api.catalogo.filmes.domain.keyword.Keywords;
import com.api.catalogo.filmes.domain.pagination.Page;
import com.api.catalogo.filmes.domain.review.Review;
import com.api.catalogo.filmes.domain.video.Video;

public interface IFindMovieInfo {
    Keywords searchKeywords(final int movieId, final String apiKey);
    Page<Review> searchReviews(final int movieId, final int page, final String apiKey);
    Video searchTrailers(final int movieId, final ILanguageMovie language, final String apiKey);
}
