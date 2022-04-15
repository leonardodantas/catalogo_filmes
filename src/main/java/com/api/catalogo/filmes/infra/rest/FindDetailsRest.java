package com.api.catalogo.filmes.infra.rest;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.app.rest.IFindDetailsRest;
import com.api.catalogo.filmes.domain.details.MovieDetail;
import com.api.catalogo.filmes.infra.rest.converters.MovieDetailConverter;
import com.api.catalogo.filmes.infra.rest.jsons.MovieDetailRest;
import com.api.catalogo.filmes.infra.rest.url.URLBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FindDetailsRest implements IFindDetailsRest {

    private final RestTemplate restTemplate;

    public FindDetailsRest(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public MovieDetail searchDetailsTMDB(final int movieId, final ILanguageMovie language) {

        final var urlBuilder = new URLBuilder.Builder("c769b56d9fbc89d33bd16385acf510ca")
                .movieId(movieId)
                .language(language)
                .builder();

        try {
            final var response = restTemplate.getForEntity(urlBuilder.getValue(), MovieDetailRest.class).getBody();
            return MovieDetailConverter.convert(response);
        } catch (final HttpClientErrorException error) {
            throw new ResponseStatusException(HttpStatus.valueOf(error.getRawStatusCode()), error.getResponseBodyAsString(), error);
        }
    }
}
