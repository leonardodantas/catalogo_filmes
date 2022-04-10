package com.api.catalogo.filmes.app.usecases;

import com.api.catalogo.filmes.app.utils.tmdb.Language;
import com.api.catalogo.filmes.domain.models.keyword.Keywords;
import com.api.catalogo.filmes.domain.models.pagination.PaginationDTO;
import com.api.catalogo.filmes.domain.models.review.ReviewDTO;
import com.api.catalogo.filmes.domain.models.video.Video;

public interface IFindMovieResources {

    Keywords searchKeywords(int idFilm);
    PaginationDTO<ReviewDTO> searchReviews(int movie, int page);
    Video searchTrailers(int movie, Language language);
}
