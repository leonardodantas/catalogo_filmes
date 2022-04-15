package com.api.catalogo.filmes.infra.rest;

import com.api.catalogo.filmes.app.models.IAllMovies;
import com.api.catalogo.filmes.app.rest.IFindAllMoviesRest;
import com.api.catalogo.filmes.domain.movie.Movie;
import com.api.catalogo.filmes.domain.pagination.Page;
import com.api.catalogo.filmes.infra.rest.converters.PageMovieConverter;
import com.api.catalogo.filmes.infra.rest.jsons.MovieRest;
import com.api.catalogo.filmes.infra.rest.jsons.PageRest;
import com.api.catalogo.filmes.infra.rest.url.URLBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FindAllMoviesRest implements IFindAllMoviesRest {

    private final RestTemplate restTemplate;

    public FindAllMoviesRest(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Page<Movie> searchAllMoviesByCategoryTMDB(final IAllMovies allMovies) {
        final var urlBuilder = new URLBuilder.Builder(allMovies.getApiKey())
                .typeMovie(allMovies.getTypeMovie())
                .page(allMovies.getPage())
                .language(allMovies.getLanguageMovie()).builder();
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
