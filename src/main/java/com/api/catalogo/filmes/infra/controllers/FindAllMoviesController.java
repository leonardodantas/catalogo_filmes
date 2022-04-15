package com.api.catalogo.filmes.infra.controllers;

import com.api.catalogo.filmes.app.usecases.IFindAll;
import com.api.catalogo.filmes.infra.controllers.request.AllMovies;
import com.api.catalogo.filmes.infra.controllers.request.LanguageMovieRequest;
import com.api.catalogo.filmes.infra.controllers.request.TypeMovieRequest;
import com.api.catalogo.filmes.infra.controllers.response.MovieResponse;
import com.api.catalogo.filmes.infra.controllers.response.PageResponse;
import com.api.catalogo.filmes.infra.exception.ErrorResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;

@Api(tags = "API - TMDB")
@RestController
@RequestMapping("/movies/tmdb")
public class FindAllMoviesController {

    private final IFindAll findAll;

    public FindAllMoviesController(final IFindAll findAll) {
        this.findAll = findAll;
    }

    @GetMapping
    @ApiOperation(tags = "API - TMDB", value = "Utilizada as informação inseridas como RequestMovie e Page para acessar a API do TMDB e recuperar uma lista de filmes")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = PageResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Pagina deve ser igual ou menor que 500", response = ErrorResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    @ResponseStatus(HttpStatus.OK)
    public PageResponse<MovieResponse> execute(
            @ApiParam(required = true, value = "Tipo do Filme a ser enviado", name = "request")
            @RequestParam(value = "request") final TypeMovieRequest request,
            @ApiParam(required = true, value = "Pagina que será solicitada ao servidor do TMDB", name = "page", example = "1")
            @RequestParam(value = "page") final int page,
            @ApiParam(required = true, value = "Idioma das requisições", name = "language")
            @RequestParam(value = "language") final LanguageMovieRequest language,
            @ApiParam(required = true, value = "apiKey da TMDB", name = "apiKey")
            @RequestHeader(value = "apiKey") final String apiKey) {
        final var response = findAll.execute(AllMovies.of(request, language, page, apiKey));
        final var movies = response.getResults().stream().map(MovieResponse::from).toList();
        return new PageResponse<>(page, movies, response.getTotal_pages(), response.getTotal_results());
    }
}