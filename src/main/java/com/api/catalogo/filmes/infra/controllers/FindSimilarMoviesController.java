package com.api.catalogo.filmes.infra.controllers;

import com.api.catalogo.filmes.app.usecases.IFindSimilarMovies;
import com.api.catalogo.filmes.infra.controllers.request.LanguageMovieRequest;
import com.api.catalogo.filmes.infra.controllers.response.MovieResponse;
import com.api.catalogo.filmes.infra.controllers.response.PageResponse;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;

@Api(tags = "API - TMDB")
@RestController
@RequestMapping("/movies/tmdb")
public class FindSimilarMoviesController {

    private final IFindSimilarMovies findSimilarMovies;

    public FindSimilarMoviesController(final IFindSimilarMovies findSimilarMovies) {
        this.findSimilarMovies = findSimilarMovies;
    }

    @GetMapping("similar")
    @ApiOperation(tags = "API - TMDB",
            value = "Usa um MOVIEID para listar os filmes similares")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = PageResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Palavra chave não encontrada"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public PageResponse<MovieResponse> searchSimilar(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie", example = "460465")
            @RequestParam(value = "movie") final int movieId,
            @ApiParam(required = true, value = "Pagina que será solicitada ao servidor do TMDB", name = "page", example = "1")
            @RequestParam(value = "page") final int page,
            @ApiParam(required = true, value = "Idioma das requisições", name = "language")
            @RequestParam(value = "language") final LanguageMovieRequest language,
            @ApiParam(required = true, value = "apiKey da TMDB", name = "apiKey")
            @RequestHeader(value = "apiKey") final String apiKey) {
        final var response = this.findSimilarMovies.searchSimilarMovies(movieId, page, language, apiKey);
        final var movies = response.getResults().stream().map(MovieResponse::from).toList();
        return new PageResponse<>(page, movies, response.getTotal_results(), response.getTotal_results());
    }
}
