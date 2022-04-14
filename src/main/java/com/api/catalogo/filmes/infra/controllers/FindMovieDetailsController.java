package com.api.catalogo.filmes.infra.controllers;

import com.api.catalogo.filmes.app.usecases.IFindDetails;
import com.api.catalogo.filmes.infra.controllers.request.LanguageMovieRequest;
import com.api.catalogo.filmes.domain.details.MovieDetail;
import com.api.catalogo.filmes.infra.controllers.response.MovieDetailsResponse;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.util.Objects;

@Api(tags = "API - TMDB")
@RestController
@RequestMapping("/movies/tmdb")
public class FindMovieDetailsController {

    private final IFindDetails findDetails;

    public FindMovieDetailsController(final IFindDetails findDetails) {
        this.findDetails = findDetails;
    }

    @GetMapping("details")
    @ApiOperation(tags = "API - TMDB",
            value = "Utiliza um id de um filme valido para retornar detalhes sobre o mesmo")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = MovieDetailsResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Pagina deve ser igual ou menor que 500"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    @ResponseStatus(HttpStatus.OK)
    public MovieDetailsResponse execute(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie",  example = "460465")
            @RequestParam(value = "movie") final int movieId,
            @ApiParam(required = true, value = "Idioma das requisições", name = "language")
            @RequestParam(value = "language") final LanguageMovieRequest language){
        final var response = findDetails.execute(movieId, language);
        return MovieDetailsResponse.from(response);
    }

}
