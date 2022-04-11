package com.api.catalogo.filmes.infra.rest;

import com.api.catalogo.filmes.app.rest.IFindDetailsRest;
import com.api.catalogo.filmes.app.utils.constantes.ServerConstante;
import com.api.catalogo.filmes.app.utils.exception.TreatmentHttpStatusException;
import com.api.catalogo.filmes.domain.details.MovieDetailDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FindDetailsRest implements IFindDetailsRest {

    private final RestTemplate restTemplate;
    private final TreatmentHttpStatusException treatmentHttpStatus;

    public FindDetailsRest(final RestTemplate restTemplate, final TreatmentHttpStatusException treatmentHttpStatus) {
        this.restTemplate = restTemplate;
        this.treatmentHttpStatus = treatmentHttpStatus;
    }

    @Override
    public MovieDetailDTO searchDetailsTMDB(final String url) {
        ResponseEntity<MovieDetailDTO> detailsMovie;
        try {
            detailsMovie = restTemplate.getForEntity(url, MovieDetailDTO.class);
        } catch (HttpClientErrorException error) {
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return detailsMovie.getBody();
    }
}
