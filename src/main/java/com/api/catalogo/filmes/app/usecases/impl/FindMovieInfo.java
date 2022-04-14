package com.api.catalogo.filmes.app.usecases.impl;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.app.rest.IFindInfoRest;
import com.api.catalogo.filmes.app.usecases.IFindMovieInfo;
import com.api.catalogo.filmes.infra.controllers.request.LanguageMovieRequest;
import com.api.catalogo.filmes.app.utils.tmdb.UrlTMDBFactory;
import com.api.catalogo.filmes.domain.keyword.Keywords;
import com.api.catalogo.filmes.domain.pagination.Page;
import com.api.catalogo.filmes.domain.review.Review;
import com.api.catalogo.filmes.domain.video.Video;
import org.springframework.stereotype.Service;

@Service
public class FindMovieInfo implements IFindMovieInfo {

    private final UrlTMDBFactory urlTMDBFactory;
    private final IFindInfoRest findResourcesRest;

    public FindMovieInfo(final UrlTMDBFactory urlTMDBFactory, final IFindInfoRest findResourcesRest) {
        this.urlTMDBFactory = urlTMDBFactory;
        this.findResourcesRest = findResourcesRest;
    }

    @Override
    public Keywords searchKeywords(final int movieId) {
        final var url = urlTMDBFactory.createURLForSearchKeywords(movieId);
        return findResourcesRest.searchKeywordsTMDB(url);
    }

    @Override
    public Page<Review> searchReviews(final int movieId, final int page) {
        final var url = urlTMDBFactory.createURLForReviewMovie(movieId, page);
        return findResourcesRest.searchReviewsTMDB(url);
    }

    @Override
    public Video searchTrailers(final int movieId, final ILanguageMovie language) {
        final var url = urlTMDBFactory.createURLForVideosMovie(movieId, language.getLanguage());
        return findResourcesRest.searchTrailersTMDB(url);
    }
}
