package com.api.catalogo.filmes.app.rest;

import com.api.catalogo.filmes.domain.keyword.Keywords;
import com.api.catalogo.filmes.domain.pagination.Page;
import com.api.catalogo.filmes.domain.review.Review;
import com.api.catalogo.filmes.domain.video.Video;

public interface IFindInfoRest {

    Keywords searchKeywordsTMDB(String url);
    Page<Review> searchReviewsTMDB(String url);
    Video searchTrailersTMDB(String url);
}
