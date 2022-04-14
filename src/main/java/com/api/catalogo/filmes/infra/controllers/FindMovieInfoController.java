package com.api.catalogo.filmes.infra.controllers;

import com.api.catalogo.filmes.app.usecases.IFindMovieInfo;
import com.api.catalogo.filmes.domain.keyword.Keywords;
import com.api.catalogo.filmes.infra.controllers.request.LanguageMovieRequest;
import com.api.catalogo.filmes.infra.controllers.response.KeywordsResponse;
import com.api.catalogo.filmes.infra.controllers.response.PageResponse;
import com.api.catalogo.filmes.infra.controllers.response.ReviewResponse;
import com.api.catalogo.filmes.infra.controllers.response.VideoResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;

@Api(tags = "API - TMDB")
@RestController
@RequestMapping("/movies/tmdb")
public class FindMovieInfoController {

    private final IFindMovieInfo findMovieInfo;
    public FindMovieInfoController(final IFindMovieInfo findMovieInfo) {
        this.findMovieInfo = findMovieInfo;
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
    @ResponseStatus(HttpStatus.OK)
    public KeywordsResponse searchKeywords(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie",  example = "460465")
            @RequestParam(value = "movie") final int movie){
        final var response = findMovieInfo.searchKeywords(movie);
        return KeywordsResponse.from(response);
    }

    @GetMapping("review")
    @ApiOperation(tags = "API - TMDB",
            value = "Usa um MOVIEID para mostrar as reviews de um filme")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = PageResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Palavra chave não encontrada"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public PageResponse<ReviewResponse> searchReviews(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie",  example = "460465")
            @RequestParam(value = "movie") final int movieId,
            @ApiParam(required = true, value = "Pagina que será solicitada ao servidor do TMDB", name = "page",  example = "1")
            @RequestParam(value = "page") final int page){
        final var response = this.findMovieInfo.searchReviews(movieId, page);
        final var reviews = response.getResults().stream().map(ReviewResponse::from).toList();
        return new PageResponse<>(page, reviews, response.getTotal_pages(), response.getTotal_results());
    }

    @GetMapping("videos")
    @ApiOperation(tags = "API - TMDB",
            value = "Usa um MOVIEID para mostrar os videos de um filme")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = VideoResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Palavra chave não encontrada"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public VideoResponse searchTrailers(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie",  example = "460465")
            @RequestParam(value = "movie") final int movie,
            @ApiParam(required = true, value = "Idioma das requisições", name = "language")
            @RequestParam(value = "language") final LanguageMovieRequest language){
        final var response = this.findMovieInfo.searchTrailers(movie, language);
        return VideoResponse.from(response);
    }

}
