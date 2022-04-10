package com.api.catalogo.filmes.infra.controllers;

import com.api.catalogo.filmes.app.usecases.IFindMovieResources;
import com.api.catalogo.filmes.app.usecases.IFindSimilarMovies;
import com.api.catalogo.filmes.app.utils.tmdb.Language;
import com.api.catalogo.filmes.domain.models.keyword.Keywords;
import com.api.catalogo.filmes.domain.models.movie.MovieDTO;
import com.api.catalogo.filmes.domain.models.pagination.PaginationDTO;
import com.api.catalogo.filmes.domain.models.review.ReviewDTO;
import com.api.catalogo.filmes.domain.models.video.Video;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.HttpURLConnection;
import java.util.Objects;

@Api(tags = "API - TMDB")
@RestController
@RequestMapping("/movies/tmdb")
public class FindByMovieIDController {

    private final IFindMovieResources findMovieResources;
    private final IFindSimilarMovies findSimilarMovies;

    public FindByMovieIDController(IFindMovieResources findMovieResources, IFindSimilarMovies findSimilarMovies) {
        this.findMovieResources = findMovieResources;
        this.findSimilarMovies = findSimilarMovies;
    }

    @GetMapping("keyword")
    @ApiOperation(tags = "API - TMDB",
            value = "Usa um MOVIEID para recuperar as palavras-chaves referentes ao filme")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = Keywords.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Palavra chave não encontrada"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> searchKeywords(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie",  example = "460465")
            @RequestParam(value = "movie") int movie){
        Keywords keywords = findMovieResources.searchKeywords(movie);
        if(Objects.isNull(keywords)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(keywords, HttpStatus.OK);
    }

    @GetMapping("similar")
    @ApiOperation(tags = "API - TMDB",
            value = "Usa um MOVIEID para listar os filmes similares")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = PaginationDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Palavra chave não encontrada"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> searchSimilar(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie",  example = "460465")
            @RequestParam(value = "movie") int movie,
            @ApiParam(required = true, value = "Pagina que será solicitada ao servidor do TMDB", name = "page",  example = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(required = true, value = "Idioma das requisições", name = "language")
            @RequestParam(value = "language") Language language){
        PaginationDTO<MovieDTO> response = this.findSimilarMovies.listSimilarMovies(movie, page, language);
        if(Objects.isNull(response.getResultados())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("review")
    @ApiOperation(tags = "API - TMDB",
            value = "Usa um MOVIEID para mostrar as reviews de um filme")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = PaginationDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Palavra chave não encontrada"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> searchReviews(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie",  example = "460465")
            @RequestParam(value = "movie") int movie,
            @ApiParam(required = true, value = "Pagina que será solicitada ao servidor do TMDB", name = "page",  example = "1")
            @RequestParam(value = "page") int page){
        PaginationDTO<ReviewDTO> response = this.findMovieResources.searchReviews(movie, page);
        if(Objects.isNull(response.getResultados())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("videos")
    @ApiOperation(tags = "API - TMDB",
            value = "Usa um MOVIEID para mostrar os videos de um filme")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = PaginationDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Palavra chave não encontrada"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> searchTrailers(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie",  example = "460465")
            @RequestParam(value = "movie") int movie,
            @ApiParam(required = true, value = "Idioma das requisições", name = "language")
            @RequestParam(value = "language") Language language){
        Video response = this.findMovieResources.searchTrailers(movie, language);
        if(Objects.isNull(response)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
