package com.api.catalogo.filmes.app.usecases.impl;

import com.api.catalogo.filmes.app.rest.IFindResourcesRest;
import com.api.catalogo.filmes.app.usecases.IFindMovieResources;
import com.api.catalogo.filmes.app.utils.tmdb.Language;
import com.api.catalogo.filmes.app.utils.tmdb.UrlTMDBFactory;
import com.api.catalogo.filmes.domain.keyword.Keywords;
import com.api.catalogo.filmes.domain.pagination.PaginationDTO;
import com.api.catalogo.filmes.domain.review.ReviewDTO;
import com.api.catalogo.filmes.domain.video.Video;
import org.springframework.stereotype.Service;

@Service
public class FindMovieResources implements IFindMovieResources {

    private final UrlTMDBFactory urlTMDBFactory;
    private final IFindResourcesRest findResourcesRest;

    public FindMovieResources(final UrlTMDBFactory urlTMDBFactory, final IFindResourcesRest findResourcesRest) {
        this.urlTMDBFactory = urlTMDBFactory;
        this.findResourcesRest = findResourcesRest;
    }

    @Override
    public Keywords searchKeywords(final int idFilm) {
        final var url = urlTMDBFactory.createURLForSearchKeywords(idFilm);
        return findResourcesRest.searchKeywordsTMDB(url);
    }

    @Override
    public PaginationDTO<ReviewDTO> searchReviews(final int movie, final int page) {
        final var url = urlTMDBFactory.createURLForReviewMovie(movie, page);
        return findResourcesRest.reviewsMovieTMDB(url);
    }

    @Override
    public Video searchTrailers(int movie, Language language) {
        final var url = urlTMDBFactory.createURLForVideosMovie(movie, language);
        return findResourcesRest.videosOfMovieTMDB(url);
    }
}
