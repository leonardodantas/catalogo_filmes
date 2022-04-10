package com.api.catalogo.filmes.app.utils.tmdb;

public enum RequestMovie {

    UPCOMING("upcoming"), LATEST("latest"), NOW_PLAYING("now_playing"),POPULAR("popular"), TOP_RATED("top_rated");

    private String typeRequest;

    RequestMovie(String typeRequest){
        this.typeRequest = typeRequest;
    }

    public String getTypeRequest() {
        return typeRequest;
    }
}
