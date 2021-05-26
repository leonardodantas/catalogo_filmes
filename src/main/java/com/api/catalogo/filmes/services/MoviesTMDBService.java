package com.api.catalogo.filmes.services;

import com.api.catalogo.filmes.models.MovieDetailDTO;
import com.api.catalogo.filmes.models.PaginationMoviesDTO;
import com.api.catalogo.filmes.models.keyword.Keywords;
import com.api.catalogo.filmes.utils.constantes.ServerConstante;
import com.api.catalogo.filmes.utils.exception.TreatmentHttpStatusException;
import com.api.catalogo.filmes.utils.tmdb.RequestMovie;
import com.api.catalogo.filmes.utils.tmdb.UrlTMDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class MoviesTMDBService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UrlTMDBFactory urlTMDBFactory;

    @Autowired
    private TreatmentHttpStatusException treatmentHttpStatus;

    public PaginationMoviesDTO searchAllMoviesByCategory(RequestMovie requestMovie, int page){
        String url = urlTMDBFactory.createURLForSearhMoviePerType(requestMovie, page);
        ResponseEntity<PaginationMoviesDTO> movies = searchAllMoviesByCategoryTMDB(url);
        if(movies.getStatusCode().equals(HttpStatus.OK) && !Objects.isNull(movies.getBody().getResultados())){
            return movies.getBody();
        }
        return new PaginationMoviesDTO();
    }

    private ResponseEntity<PaginationMoviesDTO> searchAllMoviesByCategoryTMDB(String url){
        ResponseEntity<PaginationMoviesDTO> movies;
        try {
            movies = restTemplate.getForEntity(url, PaginationMoviesDTO.class);
        } catch (HttpClientErrorException error){
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return movies;
    }

    public MovieDetailDTO searchMovieDetail(int movie) {
        String url = urlTMDBFactory.criarUrlParaBuscarDetalhesDoFilmes(movie);
        ResponseEntity<MovieDetailDTO> movieDetaiResponse = searchDetailsTMDB(url);
        if(movieDetaiResponse.getStatusCode().equals(HttpStatus.OK)){
            return Objects.requireNonNull(movieDetaiResponse.getBody());
        }
        return new MovieDetailDTO();
    }

    private ResponseEntity<MovieDetailDTO> searchDetailsTMDB(String url){
        ResponseEntity<MovieDetailDTO> detailsMovie;
        try {
            detailsMovie = restTemplate.getForEntity(url, MovieDetailDTO.class);
        } catch (HttpClientErrorException error) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return detailsMovie;
    }

    public Keywords searchKeywordsFilm(int idFilm) {
        String url = urlTMDBFactory.createURLForSearchKeywords(idFilm);
        ResponseEntity<Keywords> keywordsResponse = searchKeywordsTMDB(url);
        if(keywordsResponse.getStatusCode().equals(HttpStatus.OK)){
            return Objects.requireNonNull(keywordsResponse.getBody());
        }
        return new Keywords();
    }

    private ResponseEntity<Keywords> searchKeywordsTMDB(String url) {
        ResponseEntity<Keywords> keywords;
        try {
            keywords = restTemplate.getForEntity(url, Keywords.class);
        } catch (HttpClientErrorException error) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return keywords;
    }

    public PaginationMoviesDTO listSimilarMovies(int movie, int page) {
        String url = urlTMDBFactory.createURLForListSimilarMovies(movie, page);
        ResponseEntity<PaginationMoviesDTO> moviesResponse = listSimilarMoviesTMDB(url);
        if(moviesResponse.getStatusCode().equals(HttpStatus.OK) && !Objects.isNull(moviesResponse.getBody().getResultados())){
            return moviesResponse.getBody();
        }
        return new PaginationMoviesDTO();
    }

    private ResponseEntity<PaginationMoviesDTO> listSimilarMoviesTMDB(String url) {
        ResponseEntity<PaginationMoviesDTO> movies;
        try {
            movies = restTemplate.getForEntity(url, PaginationMoviesDTO.class);
        } catch (HttpClientErrorException error){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return movies;
    }
}
