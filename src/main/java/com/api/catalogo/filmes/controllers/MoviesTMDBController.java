package com.api.catalogo.filmes.controllers;

import com.api.catalogo.filmes.models.MovieDetailDTO;
import com.api.catalogo.filmes.models.PaginationMoviesDTO;
import com.api.catalogo.filmes.models.keyword.Keywords;
import com.api.catalogo.filmes.services.MoviesTMDBService;
import com.api.catalogo.filmes.utils.exception.ErrorResponse;
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
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = PaginationMoviesDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Pagina deve ser igual ou menor que 500", response = ErrorResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> searchAllMoviesTMDB(
            @ApiParam(required = true, value = "Tipo do Filme a ser enviado", name = "requestMovie")
            @RequestParam(value = "requestMovie") RequestMovie requestMovie,
            @ApiParam(required = true, value = "Pagina que será solicitada ao servidor do TMDB", name = "page",  example = "1")
            @RequestParam(value = "page") int page){
        PaginationMoviesDTO paginationMoviesDTO = moviesTMDBService.searchAllMoviesByCategory(requestMovie, page);
        if(Objects.isNull(paginationMoviesDTO.getResultados())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(paginationMoviesDTO, HttpStatus.OK);
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
            @RequestParam(value = "movie") int movie){
        MovieDetailDTO movieDetailDTO = moviesTMDBService.searchMovieDetail(movie);
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
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = PaginationMoviesDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Palavra chave não encontrada"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> listSimilarMovies(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie",  example = "460465")
            @RequestParam(value = "movie") int idFilme,
            @ApiParam(required = true, value = "Pagina que será solicitada ao servidor do TMDB", name = "page",  example = "1")
            @RequestParam(value = "page") int page){
        PaginationMoviesDTO paginationMoviesDTO = moviesTMDBService.listSimilarMovies(idFilme, page);
        if(Objects.isNull(paginationMoviesDTO.getResultados())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(paginationMoviesDTO, HttpStatus.OK);
    }


}
