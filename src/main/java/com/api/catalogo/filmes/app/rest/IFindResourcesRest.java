package com.api.catalogo.filmes.app.rest;

import com.api.catalogo.filmes.domain.keyword.Keywords;
import com.api.catalogo.filmes.domain.pagination.PaginationDTO;
import com.api.catalogo.filmes.domain.review.ReviewDTO;
import com.api.catalogo.filmes.domain.video.Video;

public interface IFindResourcesRest {

    Keywords searchKeywordsTMDB(String url);
    PaginationDTO<ReviewDTO> reviewsMovieTMDB(String url);
    Video videosOfMovieTMDB(String url);
}
