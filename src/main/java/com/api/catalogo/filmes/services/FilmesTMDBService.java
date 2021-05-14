package com.api.catalogo.filmes.services;

import com.api.catalogo.filmes.models.FilmeDetalheDTO;
import com.api.catalogo.filmes.models.PaginacaoFilmesDTO;
import com.api.catalogo.filmes.utils.constantes.FilmesTMDBConstante;
import com.api.catalogo.filmes.utils.constantes.ServerConstante;
import com.api.catalogo.filmes.utils.tmdb.RequestMovie;
import com.api.catalogo.filmes.utils.tmdb.UrlTMDBFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class FilmesTMDBService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UrlTMDBFactory urlTMDBFactory;

    public PaginacaoFilmesDTO buscarFilmesPorCategoria(RequestMovie requestMovie, int page){
        return buscaFilmeNoTMDB(requestMovie,page);
    }

    private PaginacaoFilmesDTO buscaFilmeNoTMDB(RequestMovie requestMovie, int page){
            PaginacaoFilmesDTO paginacaoFilmesDTO = new PaginacaoFilmesDTO();
        try {
            String url = urlTMDBFactory.criarUrlParaBuscarTiposDeFilmes(requestMovie, page);
            ResponseEntity<PaginacaoFilmesDTO> filmes = restTemplate.getForEntity(url, PaginacaoFilmesDTO.class);
            if(filmes.getStatusCode().equals(HttpStatus.OK) && !Objects.isNull(filmes.getBody().getResultados())){
                paginacaoFilmesDTO = filmes.getBody();
            }
        } catch (HttpClientErrorException error){
            if(Objects.requireNonNull(error.getMessage()).contains(FilmesTMDBConstante.ERROR_PAGE_BIGGER_500_SERVER)){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,FilmesTMDBConstante.ERROR_PAGE_BIGGER_500_RESPONSE);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return paginacaoFilmesDTO;
    }

    public FilmeDetalheDTO buscarDetalheFilme(int idFilme) {
        return buscaDetalhesTMDB(idFilme);
    }

    private FilmeDetalheDTO buscaDetalhesTMDB(int idFilme){
        FilmeDetalheDTO filmeDetalheDTO = new FilmeDetalheDTO();
        try {
            String url = urlTMDBFactory.criarUrlParaBuscarDetalhesDoFilmes(idFilme);
            ResponseEntity<FilmeDetalheDTO> filmeDetalhe = restTemplate.getForEntity(url, FilmeDetalheDTO.class);
            if(filmeDetalhe.getStatusCode().equals(HttpStatus.OK)){
                filmeDetalheDTO = Objects.requireNonNull(filmeDetalhe.getBody());
            }
        } catch (HttpClientErrorException error) {
            if(Objects.requireNonNull(error.getMessage()).contains(FilmesTMDBConstante.ERROR_ID_NOT_FOUND_SERVER)){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,FilmesTMDBConstante.ERROR_ID_NOT_FOUND_RESPONSE);
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ServerConstante.SERVER_BAD_REQUEST);
        }
        return filmeDetalheDTO;
    }

}
