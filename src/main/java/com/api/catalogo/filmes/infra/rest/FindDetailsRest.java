package com.api.catalogo.filmes.infra.rest;

import com.api.catalogo.filmes.app.rest.IFindDetailsRest;
import com.api.catalogo.filmes.domain.details.MovieDetail;
import com.api.catalogo.filmes.infra.rest.converters.MovieDetailConverter;
import com.api.catalogo.filmes.infra.rest.jsons.MovieDetailRest;
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
    public MovieDetail searchDetailsTMDB(final String url) {
        try {
            final var response = restTemplate.getForEntity(url, MovieDetailRest.class).getBody();
            return MovieDetailConverter.convert(response);
        } catch (final HttpClientErrorException error) {
            throw new ResponseStatusException(HttpStatus.valueOf(error.getRawStatusCode()), error.getResponseBodyAsString(), error);
        }
    }
}
