package com.api.catalogo.filmes.app.usecases.impl;

import com.api.catalogo.filmes.app.usecases.IFindDetails;
import com.api.catalogo.filmes.app.utils.constantes.ServerConstante;
import com.api.catalogo.filmes.app.utils.exception.TreatmentHttpStatusException;
import com.api.catalogo.filmes.app.utils.tmdb.Language;
import com.api.catalogo.filmes.app.utils.tmdb.UrlTMDBFactory;
import com.api.catalogo.filmes.domain.models.details.MovieDetailDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class FindDetails implements IFindDetails {

    private final RestTemplate restTemplate;

    private final UrlTMDBFactory urlTMDBFactory;

    private final String urlImageTMDB;

    private final TreatmentHttpStatusException treatmentHttpStatus;

    public FindDetails(RestTemplate restTemplate, UrlTMDBFactory urlTMDBFactory, @Value("${url.image.tmdb}") String urlImageTMDB, TreatmentHttpStatusException treatmentHttpStatus) {
        this.restTemplate = restTemplate;
        this.urlTMDBFactory = urlTMDBFactory;
        this.urlImageTMDB = urlImageTMDB;
        this.treatmentHttpStatus = treatmentHttpStatus;
    }

    @Override
    public MovieDetailDTO execute(int movie, Language language) {
        String url = urlTMDBFactory.criarUrlParaBuscarDetalhesDoFilmes(movie, language);
        ResponseEntity<MovieDetailDTO> movieDetaiResponse = searchDetailsTMDB(url);
        if (movieDetaiResponse.getStatusCode().equals(HttpStatus.OK)) {
            if (!Objects.isNull(movieDetaiResponse.getBody())) {
                return createURLCompleteMovieDetail(movieDetaiResponse);
            }
        }
        return new MovieDetailDTO();
    }

    private ResponseEntity<MovieDetailDTO> searchDetailsTMDB(String url) {
        ResponseEntity<MovieDetailDTO> detailsMovie;
        try {
            detailsMovie = restTemplate.getForEntity(url, MovieDetailDTO.class);
        } catch (HttpClientErrorException error) {
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return detailsMovie;
    }

    private MovieDetailDTO createURLCompleteMovieDetail(ResponseEntity<MovieDetailDTO> movieDetaiResponse) {
        MovieDetailDTO movieDetailDTO = movieDetaiResponse.getBody();
        movieDetailDTO.setCaminhoDoPoster(urlImageTMDB + movieDetailDTO.getCaminhoDoPoster());
        movieDetailDTO.setCaminhoDaImagem(urlImageTMDB + movieDetailDTO.getCaminhoDaImagem());
        movieDetailDTO.getCidadesDeProducoes().forEach(cidade -> cidade.setCaminhoDoLogo(urlImageTMDB + cidade.getCaminhoDoLogo()));
        return movieDetailDTO;
    }
}
