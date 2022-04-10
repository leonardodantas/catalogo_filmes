package com.api.catalogo.filmes.app.usecases.impl;

import com.api.catalogo.filmes.app.usecases.IFindMovieResources;
import com.api.catalogo.filmes.app.utils.constantes.ServerConstante;
import com.api.catalogo.filmes.app.utils.exception.TreatmentHttpStatusException;
import com.api.catalogo.filmes.app.utils.tmdb.Language;
import com.api.catalogo.filmes.app.utils.tmdb.UrlTMDBFactory;
import com.api.catalogo.filmes.domain.keyword.Keywords;
import com.api.catalogo.filmes.domain.pagination.PaginationDTO;
import com.api.catalogo.filmes.domain.review.ReviewDTO;
import com.api.catalogo.filmes.domain.video.Video;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class FindMovieResources implements IFindMovieResources {

    private final RestTemplate restTemplate;

    private final UrlTMDBFactory urlTMDBFactory;

    private final String urlVideoYoutube;

    private final TreatmentHttpStatusException treatmentHttpStatus;

    public FindMovieResources(RestTemplate restTemplate, UrlTMDBFactory urlTMDBFactory, @Value("${url.video.youtube}")
            String urlVideoYoutube, TreatmentHttpStatusException treatmentHttpStatus) {
        this.restTemplate = restTemplate;
        this.urlTMDBFactory = urlTMDBFactory;
        this.urlVideoYoutube = urlVideoYoutube;
        this.treatmentHttpStatus = treatmentHttpStatus;
    }

    @Override
    public Keywords searchKeywords(int idFilm) {
        String url = urlTMDBFactory.createURLForSearchKeywords(idFilm);
        ResponseEntity<Keywords> keywordsResponse = searchKeywordsTMDB(url);
        if (keywordsResponse.getStatusCode().equals(HttpStatus.OK)) {
            return Objects.requireNonNull(keywordsResponse.getBody());
        }
        return new Keywords();
    }


    private ResponseEntity<Keywords> searchKeywordsTMDB(String url) {
        ResponseEntity<Keywords> keywords;
        try {
            keywords = restTemplate.getForEntity(url, Keywords.class);
        } catch (HttpClientErrorException error) {
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return keywords;
    }

    @Override
    public PaginationDTO<ReviewDTO> searchReviews(int movie, int page) {
        String url = urlTMDBFactory.createURLForReviewMovie(movie, page);
        ResponseEntity<PaginationDTO<ReviewDTO>> moviesResponse = reviewsMovieTMDB(url);
        if (moviesResponse.getStatusCode().equals(HttpStatus.OK) && !Objects.isNull(moviesResponse.getBody().getResults())) {
            return moviesResponse.getBody();
        }
        return new PaginationDTO();
    }

    private ResponseEntity<PaginationDTO<ReviewDTO>> reviewsMovieTMDB(String url) {
        ResponseEntity<PaginationDTO<ReviewDTO>> movies;
        try {
            ParameterizedTypeReference<PaginationDTO<ReviewDTO>> typeRef = new ParameterizedTypeReference<PaginationDTO<ReviewDTO>>() {
            };
            movies = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
        } catch (HttpClientErrorException error) {
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return movies;
    }


    @Override
    public Video searchTrailers(int movie, Language language) {
        String url = urlTMDBFactory.createURLForVideosMovie(movie, language);
        ResponseEntity<Video> videos = videosOfMovieTMDB(url);
        if (videos.getStatusCode().equals(HttpStatus.OK) && !Objects.isNull(videos.getBody())) {
            videos.getBody().getResults()
                    .stream()
                    .filter(video -> video.getSite().equals("YouTube"))
                    .forEach(video -> video.setUrlYoutube(urlVideoYoutube + video.getKey()));
            return videos.getBody();
        }
        return new Video();
    }

    private ResponseEntity<Video> videosOfMovieTMDB(String url) {
        ResponseEntity<Video> videoResponse;
        try {
            videoResponse = restTemplate.getForEntity(url, Video.class);
        } catch (HttpClientErrorException error) {
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return videoResponse;
    }
}
