package com.api.catalogo.filmes.infra.controllers;

import com.api.catalogo.filmes.app.usecases.IFindDetails;
import com.api.catalogo.filmes.app.utils.tmdb.Language;
import com.api.catalogo.filmes.domain.details.MovieDetailDTO;
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
public class FindMovieDetailsController {

    private final IFindDetails findDetails;

    public FindMovieDetailsController(IFindDetails findDetails) {
        this.findDetails = findDetails;
    }

    @GetMapping("details")
    @ApiOperation(tags = "API - TMDB",
            value = "Utiliza um id de um filme valido para retornar detalhes sobre o mesmo")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = MovieDetailDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Pagina deve ser igual ou menor que 500"),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> execute(
            @ApiParam(required = true, value = "ID do Filmes que será solicitado ao servidor do TMDB", name = "movie",  example = "460465")
            @RequestParam(value = "movie") int movie,
            @ApiParam(required = true, value = "Idioma das requisições", name = "language")
            @RequestParam(value = "language") Language language){
        MovieDetailDTO response = findDetails.execute(movie, language);
        if(Objects.isNull(response)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
