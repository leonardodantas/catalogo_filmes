package com.api.catalogo.filmes.infra.rest;

import com.api.catalogo.filmes.app.rest.IFindSimilarRest;
import com.api.catalogo.filmes.app.utils.constantes.ServerConstante;
import com.api.catalogo.filmes.app.utils.exception.TreatmentHttpStatusException;
import com.api.catalogo.filmes.domain.movie.MovieDTO;
import com.api.catalogo.filmes.domain.pagination.PaginationDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FindSimilarRest implements IFindSimilarRest {

    private final RestTemplate restTemplate;
    private final TreatmentHttpStatusException treatmentHttpStatus;

    public FindSimilarRest(final RestTemplate restTemplate, final TreatmentHttpStatusException treatmentHttpStatus) {
        this.restTemplate = restTemplate;
        this.treatmentHttpStatus = treatmentHttpStatus;
    }

    @Override
    public PaginationDTO<MovieDTO> listSimilarMoviesTMDB(final String url) {
        ResponseEntity<PaginationDTO<MovieDTO>> movies;
        try {
            final var typeRef = new ParameterizedTypeReference<PaginationDTO<MovieDTO>>() {
            };
            movies = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
        } catch (HttpClientErrorException error) {
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return movies.getBody();
    }
}
