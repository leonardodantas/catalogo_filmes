package com.api.catalogo.filmes.services;

import com.api.catalogo.filmes.models.details.MovieDetailDTO;
import com.api.catalogo.filmes.models.movie.MovieDTO;
import com.api.catalogo.filmes.models.pagination.PaginationDTO;
import com.api.catalogo.filmes.models.keyword.Keywords;
import com.api.catalogo.filmes.models.review.ReviewDTO;
import com.api.catalogo.filmes.models.video.Video;
import com.api.catalogo.filmes.utils.constantes.ServerConstante;
import com.api.catalogo.filmes.utils.exception.TreatmentHttpStatusException;
import com.api.catalogo.filmes.utils.tmdb.Language;
import com.api.catalogo.filmes.utils.tmdb.RequestMovie;
import com.api.catalogo.filmes.utils.tmdb.UrlTMDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MoviesTMDBService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UrlTMDBFactory urlTMDBFactory;

    @Value("${url.image.tmdb}")
    private String urlImageTMDB;

    @Value("${url.video.youtube}")
    private String urlVideoYoutube;

    @Autowired
    private TreatmentHttpStatusException treatmentHttpStatus;

    public PaginationDTO<MovieDTO> searchAllMoviesByCategory(RequestMovie requestMovie, int page, Language language){
        String url = urlTMDBFactory.createURLForSearhMoviePerType(requestMovie, page, language);
        ResponseEntity<PaginationDTO<MovieDTO>> movies = searchAllMoviesByCategoryTMDB(url);
        if(movies.getStatusCode().equals(HttpStatus.OK) && !Objects.isNull(movies.getBody().getResultados())){
            return createURLCompleteMovie(movies);
        }
        return new PaginationDTO();
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

    private ResponseEntity<PaginationDTO<MovieDTO>> searchAllMoviesByCategoryTMDB(String url){
        ResponseEntity<PaginationDTO<MovieDTO>> movies;
        try {
            ParameterizedTypeReference<PaginationDTO<MovieDTO>> typeRef = new ParameterizedTypeReference<PaginationDTO<MovieDTO>>() {};
            movies = restTemplate.exchange(url, HttpMethod.GET,  null, typeRef);
        } catch (HttpClientErrorException error){
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return movies;
    }

    public MovieDetailDTO searchMovieDetail(int movie, Language language) {
        String url = urlTMDBFactory.criarUrlParaBuscarDetalhesDoFilmes(movie, language);
        ResponseEntity<MovieDetailDTO> movieDetaiResponse = searchDetailsTMDB(url);
        if(movieDetaiResponse.getStatusCode().equals(HttpStatus.OK)){
            if(!Objects.isNull(movieDetaiResponse.getBody())) {
                return createURLCompleteMovieDetail(movieDetaiResponse);
            }
        }
        return new MovieDetailDTO();
    }

    private MovieDetailDTO createURLCompleteMovieDetail(ResponseEntity<MovieDetailDTO> movieDetaiResponse) {
        MovieDetailDTO movieDetailDTO = movieDetaiResponse.getBody();
        movieDetailDTO.setCaminhoDoPoster(urlImageTMDB + movieDetailDTO.getCaminhoDoPoster());
        movieDetailDTO.setCaminhoDaImagem(urlImageTMDB + movieDetailDTO.getCaminhoDaImagem());
        movieDetailDTO.getCidadesDeProducoes()
                .forEach(cidade -> cidade.setCaminhoDoLogo(urlImageTMDB + cidade.getCaminhoDoLogo()));
        return movieDetailDTO;
    }

    private ResponseEntity<MovieDetailDTO> searchDetailsTMDB(String url){
        ResponseEntity<MovieDetailDTO> detailsMovie;
        try {
            detailsMovie = restTemplate.getForEntity(url, MovieDetailDTO.class);
        } catch (HttpClientErrorException error) {
            treatmentHttpStatus.handlerHttpStatusException(error);
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
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return keywords;
    }

    public PaginationDTO<MovieDTO> listSimilarMovies(int movie, int page, Language language) {
        String url = urlTMDBFactory.createURLForListSimilarMovies(movie, page, language);
        ResponseEntity<PaginationDTO<MovieDTO>> moviesResponse = listSimilarMoviesTMDB(url);
        if(moviesResponse.getStatusCode().equals(HttpStatus.OK) && !Objects.isNull(moviesResponse.getBody().getResultados())){
            return createURLCompleteMovie(moviesResponse);
        }
        return new PaginationDTO();
    }

    private ResponseEntity<PaginationDTO<MovieDTO>> listSimilarMoviesTMDB(String url) {
        ResponseEntity<PaginationDTO<MovieDTO>> movies;
        try {
            ParameterizedTypeReference<PaginationDTO<MovieDTO>> typeRef = new ParameterizedTypeReference<PaginationDTO<MovieDTO>>() {};
            movies = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
        } catch (HttpClientErrorException error){
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return movies;
    }

    public PaginationDTO<ReviewDTO> reviewsMovie(int movie, int page) {
        String url = urlTMDBFactory.createURLForReviewMovie(movie, page);
        ResponseEntity<PaginationDTO<ReviewDTO>> moviesResponse = reviewsMovieTMDB(url);
        if(moviesResponse.getStatusCode().equals(HttpStatus.OK) && !Objects.isNull(moviesResponse.getBody().getResultados())){
            return moviesResponse.getBody();
        }
        return new PaginationDTO();
    }

    private ResponseEntity<PaginationDTO<ReviewDTO>> reviewsMovieTMDB(String url) {
        ResponseEntity<PaginationDTO<ReviewDTO>> movies;
        try {
            ParameterizedTypeReference<PaginationDTO<ReviewDTO>> typeRef = new ParameterizedTypeReference<PaginationDTO<ReviewDTO>>() {};
            movies = restTemplate.exchange(url, HttpMethod.GET, null, typeRef);
        } catch (HttpClientErrorException error){
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return movies;
    }

    public Video videosOfMovie(int movie, Language language) {
        String url = urlTMDBFactory.createURLForVideosMovie(movie, language);
        ResponseEntity<Video> videos = videosOfMovieTMDB(url);
        if(videos.getStatusCode().equals(HttpStatus.OK) && !Objects.isNull(videos.getBody())){
            videos.getBody().getResults()
                    .stream()
                    .filter(video -> video.getSite().equals("YouTube"))
                    .forEach(video -> video.setUrlYoutube(urlVideoYoutube + video.getKey()));
            return videos.getBody();
        }
        return new Video();
    }

    private ResponseEntity<Video> videosOfMovieTMDB(String url) {
        ResponseEntity<Video> videoResponse;
        try {
            videoResponse = restTemplate.getForEntity(url, Video.class);
        } catch (HttpClientErrorException error){
            treatmentHttpStatus.handlerHttpStatusException(error);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return videoResponse;
    }
}
