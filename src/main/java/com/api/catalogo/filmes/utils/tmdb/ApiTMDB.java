package com.api.catalogo.filmes.utils.tmdb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApiTMDB {

    @Value("${api.key}")
    private String apiKey;

    @Value("${url.movie}")
    private String urlMovie;

    public String getApiKey() {
        return apiKey;
    }

    public String getUrlMovie() {
        return urlMovie;
    }

    public String criarUrlMovie(RequestMovie requestMovie){
        return urlMovie + requestMovie.getTypeRequest() + "?api_key=" + apiKey;
    }

}
