package com.api.catalogo.filmes.app.utils.tmdb;

import com.api.catalogo.filmes.app.utils.constantes.MoviesTMDBConstants;
import com.api.catalogo.filmes.infra.controllers.request.LanguageMovieRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlTMDBFactory {

    @Value("${api.key}")
    private String apiKey;

    @Value("${url.movie}")
    private String urlMovie;

    public String createURLForSearhMoviePerType(final String type, final int page, String language){
        return urlMovie + type + MoviesTMDBConstants.URL_API_KEY + apiKey + MoviesTMDBConstants.URL_PAGE + page + language;
    }

    public String criarUrlParaBuscarDetalhesDoFilmes(final int movieId, final String language){
        return urlMovie + movieId + MoviesTMDBConstants.URL_API_KEY + apiKey + language;
    }

    public String createURLForSearchKeywords(int movieId) {
        return urlMovie + movieId + MoviesTMDBConstants.URL_KEYWORDS +  MoviesTMDBConstants.URL_API_KEY + apiKey;
    }

    public String createURLForListSimilarMovies(int movie, int page, String language) {
        return urlMovie + movie + MoviesTMDBConstants.URL_SIMILAR +  MoviesTMDBConstants.URL_API_KEY + apiKey + MoviesTMDBConstants.URL_PAGE + page + language;
    }

    public String createURLForReviewMovie(int movie, int page) {
        return urlMovie + movie + MoviesTMDBConstants.URL_REVIWES +  MoviesTMDBConstants.URL_API_KEY + apiKey + MoviesTMDBConstants.URL_PAGE + page;
    }

    public String createURLForVideosMovie(int movie, String language) {
        return urlMovie + movie + MoviesTMDBConstants.URL_VIDEOS + MoviesTMDBConstants.URL_API_KEY + apiKey + language;
    }
}
