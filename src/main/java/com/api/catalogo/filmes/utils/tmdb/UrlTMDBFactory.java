package com.api.catalogo.filmes.utils.tmdb;

import com.api.catalogo.filmes.utils.constantes.MoviesTMDBConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlTMDBFactory {

    @Value("${api.key}")
    private String apiKey;

    @Value("${url.movie}")
    private String urlMovie;

    public String createURLForSearhMoviePerType(RequestMovie requestMovie, int page, Language language){
        return urlMovie + requestMovie.getTypeRequest() + MoviesTMDBConstants.URL_API_KEY + apiKey + MoviesTMDBConstants.URL_PAGE + page + language.getLanguage();
    }

    public String criarUrlParaBuscarDetalhesDoFilmes(int movieId, Language language){
        return urlMovie + movieId + MoviesTMDBConstants.URL_API_KEY + apiKey + language.getLanguage();
    }

    public String createURLForSearchKeywords(int movieId) {
        return urlMovie + movieId + MoviesTMDBConstants.URL_KEYWORDS +  MoviesTMDBConstants.URL_API_KEY + apiKey;
    }

    public String createURLForListSimilarMovies(int movie, int page, Language language) {
        return urlMovie + movie + MoviesTMDBConstants.URL_SIMILAR +  MoviesTMDBConstants.URL_API_KEY + apiKey + MoviesTMDBConstants.URL_PAGE + page + language.getLanguage();
    }

    public String createURLForReviewMovie(int movie, int page) {
        return urlMovie + movie + MoviesTMDBConstants.URL_REVIWES +  MoviesTMDBConstants.URL_API_KEY + apiKey + MoviesTMDBConstants.URL_PAGE + page;
    }

    public String createURLForVideosMovie(int movie, Language language) {
        return urlMovie + movie + MoviesTMDBConstants.URL_VIDEOS + MoviesTMDBConstants.URL_API_KEY + apiKey + language.getLanguage();
    }
}
