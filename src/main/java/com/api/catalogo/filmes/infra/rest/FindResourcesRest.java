package com.api.catalogo.filmes.infra.rest;

import com.api.catalogo.filmes.app.rest.IFindResourcesRest;
import com.api.catalogo.filmes.app.utils.constantes.ServerConstante;
import com.api.catalogo.filmes.app.utils.exception.TreatmentHttpStatusException;
import com.api.catalogo.filmes.domain.keyword.Keywords;
import com.api.catalogo.filmes.domain.pagination.PaginationDTO;
import com.api.catalogo.filmes.domain.review.ReviewDTO;
import com.api.catalogo.filmes.domain.video.Video;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FindResourcesRest implements IFindResourcesRest {

    private final RestTemplate restTemplate;
    private final TreatmentHttpStatusException treatmentHttpStatus;

    public FindResourcesRest(final RestTemplate restTemplate, final TreatmentHttpStatusException treatmentHttpStatus) {
        this.restTemplate = restTemplate;
        this.treatmentHttpStatus = treatmentHttpStatus;
    }

    @Override
    public Keywords searchKeywordsTMDB(final String url) {
        ResponseEntity<Keywords> keywords;
        try {
            keywords = restTemplate.getForEntity(url, Keywords.class);
        } catch (final HttpClientErrorException error) {
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return keywords.getBody();
    }

    @Override
    public PaginationDTO<ReviewDTO> reviewsMovieTMDB(final String url) {
        ResponseEntity<PaginationDTO<ReviewDTO>> movies;
        try {
            final var typeRef = new ParameterizedTypeReference<PaginationDTO<ReviewDTO>>() {
            };
            movies = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
        } catch (final HttpClientErrorException error) {
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return movies.getBody();
    }

    @Override
    public Video videosOfMovieTMDB(final String url) {
        ResponseEntity<Video> videoResponse;
        try {
            videoResponse = restTemplate.getForEntity(url, Video.class);
        } catch (final HttpClientErrorException error) {
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return videoResponse.getBody();
    }
}
