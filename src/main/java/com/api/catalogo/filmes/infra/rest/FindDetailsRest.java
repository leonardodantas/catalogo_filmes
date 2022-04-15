package com.api.catalogo.filmes.infra.rest;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.app.rest.IFindDetailsRest;
import com.api.catalogo.filmes.domain.details.MovieDetail;
import com.api.catalogo.filmes.infra.exception.ExceptionHandling;
import com.api.catalogo.filmes.infra.rest.converters.MovieDetailConverter;
import com.api.catalogo.filmes.infra.rest.jsons.MovieDetailRest;
import com.api.catalogo.filmes.infra.rest.url.URLBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Component
public class FindDetailsRest implements IFindDetailsRest {

    private final RestTemplate restTemplate;
    private final ExceptionHandling exceptionHandling;

    public FindDetailsRest(final RestTemplate restTemplate, final ExceptionHandling exceptionHandling) {
        this.restTemplate = restTemplate;
        this.exceptionHandling = exceptionHandling;
    }

    @Override
    public MovieDetail searchDetailsTMDB(final int movieId, final ILanguageMovie language, final String apiKey) {

        final var urlBuilder = new URLBuilder.Builder(apiKey)
                .movieId(movieId)
                .language(language)
                .builder();

        try {
            final var response = Optional.of(restTemplate.getForEntity(urlBuilder.getValue(), MovieDetailRest.class));
            return response.map(ResponseEntity::getBody)
                    .map(MovieDetailConverter::convert)
                    .orElseThrow((() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found")));

        } catch (final HttpClientErrorException error) {
            final var message = exceptionHandling.getMessage(error.getResponseBodyAsString());
            throw new ResponseStatusException(HttpStatus.valueOf(error.getRawStatusCode()), message);
        }
    }
}
