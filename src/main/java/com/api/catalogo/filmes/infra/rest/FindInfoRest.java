package com.api.catalogo.filmes.infra.rest;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.app.rest.IFindInfoRest;
import com.api.catalogo.filmes.domain.keyword.Keywords;
import com.api.catalogo.filmes.domain.pagination.Page;
import com.api.catalogo.filmes.domain.review.Review;
import com.api.catalogo.filmes.domain.video.Video;
import com.api.catalogo.filmes.infra.exception.ExceptionHandling;
import com.api.catalogo.filmes.infra.rest.converters.KeywordsConverter;
import com.api.catalogo.filmes.infra.rest.converters.PageReviewConverter;
import com.api.catalogo.filmes.infra.rest.converters.VideoConverter;
import com.api.catalogo.filmes.infra.rest.jsons.KeywordsRest;
import com.api.catalogo.filmes.infra.rest.jsons.PageRest;
import com.api.catalogo.filmes.infra.rest.jsons.ReviewRest;
import com.api.catalogo.filmes.infra.rest.jsons.VideoRest;
import com.api.catalogo.filmes.infra.rest.url.Resource;
import com.api.catalogo.filmes.infra.rest.url.URLBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FindInfoRest implements IFindInfoRest {

    private final RestTemplate restTemplate;
    private final ExceptionHandling exceptionHandling;


    public FindInfoRest(final RestTemplate restTemplate, final ExceptionHandling exceptionHandling) {
        this.restTemplate = restTemplate;
        this.exceptionHandling = exceptionHandling;
    }

    @Override
    public Keywords searchKeywordsTMDB(final int movieId, final String apiKey) {
        final var urlBuilder = new URLBuilder.Builder(apiKey)
                .movieId(movieId)
                .resource(Resource.KEYWORDS)
                .builder();
        try {
            final var response = restTemplate.getForEntity(urlBuilder.getValue(), KeywordsRest.class).getBody();
            return KeywordsConverter.convert(response);
        } catch (final HttpClientErrorException error) {
            final var message = exceptionHandling.getMessage(error.getResponseBodyAsString());
            throw new ResponseStatusException(HttpStatus.valueOf(error.getRawStatusCode()), message);
        }
    }

    @Override
    public Page<Review> searchReviewsTMDB(final int movieId, final int page, final String apiKey) {
        final var urlBuilder = new URLBuilder.Builder(apiKey)
                .movieId(movieId)
                .resource(Resource.REVIEWS)
                .page(page)
                .builder();

        try {
            final var typeRef = new ParameterizedTypeReference<PageRest<ReviewRest>>() {
            };
            final var response = restTemplate.exchange(urlBuilder.getValue(), HttpMethod.GET, null, typeRef).getBody();
            return PageReviewConverter.convert(response);
        } catch (final HttpClientErrorException error) {
            final var message = exceptionHandling.getMessage(error.getResponseBodyAsString());
            throw new ResponseStatusException(HttpStatus.valueOf(error.getRawStatusCode()), message);
        }
    }

    @Override
    public Video searchTrailersTMDB(final int movieId, final ILanguageMovie language, final String apiKey) {
        final var urlBuilder = new URLBuilder.Builder(apiKey)
                .movieId(movieId)
                .language(language)
                .resource(Resource.VIDEOS)
                .builder();
        try {
            final var response = restTemplate.getForEntity(urlBuilder.getValue(), VideoRest.class).getBody();
            return VideoConverter.convert(response);
        } catch (final HttpClientErrorException error) {
            final var message = exceptionHandling.getMessage(error.getResponseBodyAsString());
            throw new ResponseStatusException(HttpStatus.valueOf(error.getRawStatusCode()), message);
        }
    }
}
