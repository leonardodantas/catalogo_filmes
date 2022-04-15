package com.api.catalogo.filmes.app.rest;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.domain.keyword.Keywords;
import com.api.catalogo.filmes.domain.pagination.Page;
import com.api.catalogo.filmes.domain.review.Review;
import com.api.catalogo.filmes.domain.video.Video;

public interface IFindInfoRest {

    Keywords searchKeywordsTMDB(final int movieId, final String apiKey);
    Page<Review> searchReviewsTMDB(final int movieId, final int page, final String apiKey);
    Video searchTrailersTMDB(final int movieId, final ILanguageMovie language, final String apiKey);
}
