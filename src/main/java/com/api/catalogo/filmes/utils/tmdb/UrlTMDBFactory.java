package com.api.catalogo.filmes.utils.tmdb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlTMDBFactory {

    @Value("${api.key}")
    private String apiKey;

    @Value("${url.movie}")
    private String urlMovie;

    public String createURLForSearhMoviePerType(RequestMovie requestMovie, int page){
        return urlMovie + requestMovie.getTypeRequest() + "?api_key=" + apiKey + "&page=" + page + "&language=pt-BR";
    }

    public String criarUrlParaBuscarDetalhesDoFilmes(int movieId){
        return urlMovie + movieId + "?api_key=" + apiKey + "&language=pt-BR";
    }

    public String createURLForSearchKeywords(int movieId) {
        return urlMovie + movieId + "/keywords" + "?api_key=" + apiKey;
    }

    public String createURLForListSimilarMovies(int movie, int page) {
        return urlMovie + movie + "/similar" + "?api_key=" + apiKey + "&page=" + page + "&language=pt-BR";
    }

    public String createURLForReviewMovie(int movie, int page) {
        return urlMovie + movie + "/reviews" + "?api_key=" + apiKey + "&page=" + page;
    }

    public String createURLForVideosMovie(int movie) {
        return urlMovie + movie + "/videos" + "?api_key=" + apiKey + "&language=pt-BR";
    }
}
