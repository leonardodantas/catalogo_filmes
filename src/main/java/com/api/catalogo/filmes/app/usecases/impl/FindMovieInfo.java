package com.api.catalogo.filmes.app.usecases.impl;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.app.rest.IFindInfoRest;
import com.api.catalogo.filmes.app.usecases.IFindMovieInfo;
import com.api.catalogo.filmes.domain.keyword.Keywords;
import com.api.catalogo.filmes.domain.pagination.Page;
import com.api.catalogo.filmes.domain.review.Review;
import com.api.catalogo.filmes.domain.video.Video;
import org.springframework.stereotype.Service;

@Service
public class FindMovieInfo implements IFindMovieInfo {

    private final IFindInfoRest findResourcesRest;

    public FindMovieInfo(final IFindInfoRest findResourcesRest) {
        this.findResourcesRest = findResourcesRest;
    }

    @Override
    public Keywords searchKeywords(final int movieId) {
        return findResourcesRest.searchKeywordsTMDB(movieId);
    }

    @Override
    public Page<Review> searchReviews(final int movieId, final int page) {
        return findResourcesRest.searchReviewsTMDB(movieId, page);
    }

    @Override
    public Video searchTrailers(final int movieId, final ILanguageMovie language) {
        return findResourcesRest.searchTrailersTMDB(movieId, language);
    }
}
