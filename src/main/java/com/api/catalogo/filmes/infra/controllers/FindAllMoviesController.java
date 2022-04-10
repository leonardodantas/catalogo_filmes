package com.api.catalogo.filmes.infra.controllers;

import com.api.catalogo.filmes.app.usecases.IFindAll;
import com.api.catalogo.filmes.app.utils.exception.ErrorResponse;
import com.api.catalogo.filmes.app.utils.tmdb.Language;
import com.api.catalogo.filmes.app.utils.tmdb.RequestMovie;
import com.api.catalogo.filmes.domain.models.movie.MovieDTO;
import com.api.catalogo.filmes.domain.models.pagination.PaginationDTO;
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
public class FindAllMoviesController {

    private final IFindAll findAll;

    public FindAllMoviesController(IFindAll findAll) {
        this.findAll = findAll;
    }

    @GetMapping
    @ApiOperation(tags = "API - TMDB", value = "Utilizada as informação inseridas como RequestMovie e Page para acessar a API do TMDB e recuperar uma lista de filmes")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = PaginationDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Pagina deve ser igual ou menor que 500", response = ErrorResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> execute(
            @ApiParam(required = true, value = "Tipo do Filme a ser enviado", name = "request")
            @RequestParam(value = "request") RequestMovie request,
            @ApiParam(required = true, value = "Pagina que será solicitada ao servidor do TMDB", name = "page",  example = "1")
            @RequestParam(value = "page") int page,
            @ApiParam(required = true, value = "Idioma das requisições", name = "language")
            @RequestParam(value = "language") Language language
    ){
        PaginationDTO<MovieDTO> response = findAll.execute(request, page, language);
        if(Objects.isNull(response.getResultados())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
