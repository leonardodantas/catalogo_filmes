package com.api.catalogo.filmes.infra.rest;

import com.api.catalogo.filmes.app.models.ISimilarMovies;
import com.api.catalogo.filmes.app.rest.IFindSimilarRest;
import com.api.catalogo.filmes.domain.movie.Movie;
import com.api.catalogo.filmes.domain.pagination.Page;
import com.api.catalogo.filmes.infra.rest.converters.PageMovieConverter;
import com.api.catalogo.filmes.infra.rest.jsons.MovieRest;
import com.api.catalogo.filmes.infra.rest.jsons.PageRest;
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
public class FindSimilarRest implements IFindSimilarRest {

    private final RestTemplate restTemplate;

    public FindSimilarRest(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Page<Movie> searchSimilarMoviesTMDB(final ISimilarMovies similarMovies) {
        final var urlBuilder = new URLBuilder.Builder(similarMovies.getApiKey())
                .movieId(similarMovies.getMovieId())
                .resource(Resource.SIMILAR)
                .page(similarMovies.getPage())
                .language(similarMovies.getLanguage())
                .builder();
        try {
            final var typeRef = new ParameterizedTypeReference<PageRest<MovieRest>>() {
            };
            final var response = restTemplate.exchange(urlBuilder.getValue(), HttpMethod.GET, null, typeRef).getBody();
            return PageMovieConverter.convert(response);
        } catch (final HttpClientErrorException error) {
            throw new ResponseStatusException(HttpStatus.valueOf(error.getRawStatusCode()), error.getMessage(), error);
        }
    }
}
