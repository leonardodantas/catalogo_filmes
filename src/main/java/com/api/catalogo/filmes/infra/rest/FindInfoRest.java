package com.api.catalogo.filmes.infra.rest;

import com.api.catalogo.filmes.app.rest.IFindInfoRest;
import com.api.catalogo.filmes.domain.keyword.Keywords;
import com.api.catalogo.filmes.domain.pagination.Page;
import com.api.catalogo.filmes.domain.review.Review;
import com.api.catalogo.filmes.domain.video.Video;
import com.api.catalogo.filmes.infra.rest.converters.KeywordsConverter;
import com.api.catalogo.filmes.infra.rest.converters.PageReviewConverter;
import com.api.catalogo.filmes.infra.rest.converters.VideoConverter;
import com.api.catalogo.filmes.infra.rest.jsons.KeywordsRest;
import com.api.catalogo.filmes.infra.rest.jsons.PageRest;
import com.api.catalogo.filmes.infra.rest.jsons.ReviewRest;
import com.api.catalogo.filmes.infra.rest.jsons.VideoRest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FindInfoRest implements IFindInfoRest {

    private final RestTemplate restTemplate;
    public FindInfoRest(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Keywords searchKeywordsTMDB(final String url) {
        try {
            final var response = restTemplate.getForEntity(url, KeywordsRest.class).getBody();
            return KeywordsConverter.convert(response);
        } catch (final HttpClientErrorException error) {
            throw new ResponseStatusException(HttpStatus.valueOf(error.getRawStatusCode()), error.getResponseBodyAsString(), error);
        }
    }

    @Override
    public Page<Review> searchReviewsTMDB(final String url) {
        try {
            final var typeRef = new ParameterizedTypeReference<PageRest<ReviewRest>>() {
            };
            final var response = restTemplate.exchange(url, HttpMethod.GET, null, typeRef).getBody();
            return PageReviewConverter.convert(response);
        } catch (final HttpClientErrorException error) {
            throw new ResponseStatusException(HttpStatus.valueOf(error.getRawStatusCode()), error.getResponseBodyAsString(), error);
        }
    }

    @Override
    public Video searchTrailersTMDB(final String url) {
        try {
            final var response = restTemplate.getForEntity(url, VideoRest.class).getBody();
            return VideoConverter.convert(response);
        } catch (final HttpClientErrorException error) {
            throw new ResponseStatusException(HttpStatus.valueOf(error.getRawStatusCode()), error.getResponseBodyAsString(), error);
        }
    }
}
