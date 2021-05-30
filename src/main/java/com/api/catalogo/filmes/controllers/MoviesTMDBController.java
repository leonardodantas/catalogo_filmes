package com.api.catalogo.filmes.controllers;

import com.api.catalogo.filmes.models.details.MovieDetailDTO;
import com.api.catalogo.filmes.models.movie.MovieDTO;
import com.api.catalogo.filmes.models.pagination.PaginationDTO;
import com.api.catalogo.filmes.models.keyword.Keywords;
import com.api.catalogo.filmes.models.review.ReviewDTO;
import com.api.catalogo.filmes.models.video.Video;
import com.api.catalogo.filmes.services.MoviesTMDBService;
import com.api.catalogo.filmes.utils.exception.ErrorResponse;
import com.api.catalogo.filmes.utils.tmdb.Language;
import com.api.catalogo.filmes.utils.tmdb.RequestMovie;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.Objects;

@Api(tags = "Serviço de acesso a API do TMDB")
@RestController
@RequestMapping("/movies/tmdb")
public class MoviesTMDBController {

    @Autowired
    private MoviesTMDBService moviesTMDBService;

    @GetMapping
    @ApiOperation(tags = "Serviço de acesso a API do TMDB", value = "Utilizada as informação inseridas como RequestMovie e Page para acessar a API do TMDB e recuperar uma lista de files")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = PaginationDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Pagina deve ser igual ou menor que 500", response = ErrorResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> searchAllMoviesTMDB(
            @ApiParam(required = true, value = "Tipo do Filme a ser enviado", name = "requestMovie")
            @RequestParam(value = "requestMovie") RequestMovie requestMovie,
            @ApiParam(required = true, value = "Pagina que será solicitada ao servidor do TMDB", name = "page",  example = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(required = true, value = "Idioma das requisições", name = "language")
            @RequestParam(value = "language") Language language
            ){
        PaginationDTO<MovieDTO> paginationDTO = moviesTMDBService.searchAllMoviesByCategory(requestMovie, page, language);
        if(Objects.isNull(paginationDTO.getResultados())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(paginationDTO, HttpStatus.OK);
    }

    @GetMapping("details")
    @ApiOperation(tags = "Serviço de acesso a API do TMDB",
            value = "Utiliza um id de um filme valido para retornar detalhes sobre o mesmo")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = MovieDetailDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Pagina deve ser igual ou menor que 500"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> recuperarDetalheFilmeTMDB(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie",  example = "460465")
            @RequestParam(value = "movie") int movie,
            @ApiParam(required = true, value = "Idioma das requisições", name = "language")
            @RequestParam(value = "language") Language language){
        MovieDetailDTO movieDetailDTO = moviesTMDBService.searchMovieDetail(movie, language);
        if(Objects.isNull(movieDetailDTO)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(movieDetailDTO, HttpStatus.OK);
    }

    @GetMapping("keyword")
    @ApiOperation(tags = "Serviço de acesso a API do TMDB",
            value = "Usa um MOVIEID para recuperar as palavras-chaves referentes ao filme")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = Keywords.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Palavra chave não encontrada"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> searchKeywordsTMDB(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie",  example = "460465")
            @RequestParam(value = "movie") int movie){
        Keywords keywords = moviesTMDBService.searchKeywordsFilm(movie);
        if(Objects.isNull(keywords)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(keywords, HttpStatus.OK);
    }

    @GetMapping("similar")
    @ApiOperation(tags = "Serviço de acesso a API do TMDB",
            value = "Usa um MOVIEID para listar os filmes similares")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = PaginationDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Palavra chave não encontrada"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> listSimilarMovies(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie",  example = "460465")
            @RequestParam(value = "movie") int movie,
            @ApiParam(required = true, value = "Pagina que será solicitada ao servidor do TMDB", name = "page",  example = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(required = true, value = "Idioma das requisições", name = "language")
            @RequestParam(value = "language") Language language){
        PaginationDTO<MovieDTO> paginationDTO = moviesTMDBService.listSimilarMovies(movie, page, language);
        if(Objects.isNull(paginationDTO.getResultados())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(paginationDTO, HttpStatus.OK);
    }

    @GetMapping("review")
    @ApiOperation(tags = "Serviço de acesso a API do TMDB",
            value = "Usa um MOVIEID para mostrar as reviews de um filme")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = PaginationDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Palavra chave não encontrada"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> reviewsMovie(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie",  example = "460465")
            @RequestParam(value = "movie") int movie,
            @ApiParam(required = true, value = "Pagina que será solicitada ao servidor do TMDB", name = "page",  example = "1")
            @RequestParam(value = "page") int page){
        PaginationDTO<ReviewDTO> paginationDTO = moviesTMDBService.reviewsMovie(movie, page);
        if(Objects.isNull(paginationDTO.getResultados())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(paginationDTO, HttpStatus.OK);
    }


    @GetMapping("videos")
    @ApiOperation(tags = "Serviço de acesso a API do TMDB",
            value = "Usa um MOVIEID para mostrar os videos de um filme")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = PaginationDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Palavra chave não encontrada"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> videosOfMovie(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie",  example = "460465")
            @RequestParam(value = "movie") int movie,
            @ApiParam(required = true, value = "Idioma das requisições", name = "language")
            @RequestParam(value = "language") Language language){
        Video video = moviesTMDBService.videosOfMovie(movie, language);
        if(Objects.isNull(video)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(video, HttpStatus.OK);
    }


}
