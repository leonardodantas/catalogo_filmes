package com.api.catalogo.filmes.controllers;

import com.api.catalogo.filmes.models.PaginacaoFilmesDTO;
import com.api.catalogo.filmes.services.FilmesTMDBService;
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
@RequestMapping("/filmes/tmdb")
public class FilmesTMDBController {

    @Autowired
    private FilmesTMDBService filmesTMDBService;

    @GetMapping("/{requestMovie}/{page}")
    @ApiOperation(tags = "Serviço de acesso a API do TMDB", value = "Utilizada as informação inseridas como RequestMovie e Page para acessar a API do TMDB e recuperar uma lista de files")
    @ApiResponses(value = {
            @ApiResponse(code = HttpURLConnection.HTTP_OK, message = "Returns", response = PaginacaoFilmesDTO.class),
            @ApiResponse(code = HttpURLConnection.HTTP_UNAUTHORIZED, message = "Unauthorized"),
            @ApiResponse(code = HttpURLConnection.HTTP_NO_CONTENT, message = "Pagina deve ser igual ou menor que 500", response = ErrorResponse.class),
            @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Servidor fora do ar")
    })
    public ResponseEntity<?> recuperarFilmesTMDB(
            @ApiParam(required = true, value = "Tipo do Filme a ser enviado", name = "requestMovie")
            @PathVariable(value = "requestMovie") RequestMovie requestMovie,
            @ApiParam(required = true, value = "Pagina que será solicitada ao servidor do TMDB", name = "page",  example = "1")
            @PathVariable(value = "page") int page){
        PaginacaoFilmesDTO paginacaoFilmesDTO = filmesTMDBService.buscarFilmesPorCategoria(requestMovie, page);
        if(Objects.isNull(paginacaoFilmesDTO.getResultados())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(paginacaoFilmesDTO, HttpStatus.OK);
    }
}
