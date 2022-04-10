package com.api.catalogo.filmes.app.usecases.impl;

import com.api.catalogo.filmes.app.usecases.IFindAll;
import com.api.catalogo.filmes.app.utils.constantes.ServerConstante;
import com.api.catalogo.filmes.app.utils.exception.TreatmentHttpStatusException;
import com.api.catalogo.filmes.app.utils.tmdb.Language;
import com.api.catalogo.filmes.app.utils.tmdb.RequestMovie;
import com.api.catalogo.filmes.app.utils.tmdb.UrlTMDBFactory;
import com.api.catalogo.filmes.domain.models.movie.MovieDTO;
import com.api.catalogo.filmes.domain.models.pagination.PaginationDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class FindAll implements IFindAll {

    private final RestTemplate restTemplate;

    private final UrlTMDBFactory urlTMDBFactory;

    private final TreatmentHttpStatusException treatmentHttpStatus;

    private final String urlImageTMDB;

    public FindAll(RestTemplate restTemplate, UrlTMDBFactory urlTMDBFactory, TreatmentHttpStatusException treatmentHttpStatus, @Value("${url.image.tmdb}")
            String urlImageTMDB) {
        this.restTemplate = restTemplate;
        this.urlTMDBFactory = urlTMDBFactory;
        this.treatmentHttpStatus = treatmentHttpStatus;
        this.urlImageTMDB = urlImageTMDB;
    }

    @Override
    public PaginationDTO<MovieDTO> execute(RequestMovie requestMovie, int page, Language language) {
        String url = urlTMDBFactory.createURLForSearhMoviePerType(requestMovie, page, language);
        ResponseEntity<PaginationDTO<MovieDTO>> movies = searchAllMoviesByCategoryTMDB(url);
        if (movies.getStatusCode().equals(HttpStatus.OK) && !Objects.isNull(movies.getBody().getResultados())) {
            return createURLCompleteMovie(movies);
        }
        return new PaginationDTO();
    }

    private ResponseEntity<PaginationDTO<MovieDTO>> searchAllMoviesByCategoryTMDB(String url) {
        ResponseEntity<PaginationDTO<MovieDTO>> movies;
        try {
            ParameterizedTypeReference<PaginationDTO<MovieDTO>> typeRef = new ParameterizedTypeReference<PaginationDTO<MovieDTO>>() {
            };
            movies = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
        } catch (HttpClientErrorException error) {
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return movies;
    }

    private PaginationDTO<MovieDTO> createURLCompleteMovie(ResponseEntity<PaginationDTO<MovieDTO>> movies) {
        PaginationDTO<MovieDTO> moviePageDTO = movies.getBody();
        moviePageDTO
                .getResultados()
                .forEach(result -> {
                    result.setCaminhoDaImagemDeFundo(urlImageTMDB + result.getCaminhoDaImagemDeFundo());
                    result.setCaminhoDoPoster(urlImageTMDB + result.getCaminhoDoPoster());
                });
        return moviePageDTO;
    }
}
