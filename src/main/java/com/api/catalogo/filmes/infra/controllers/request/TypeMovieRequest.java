package com.api.catalogo.filmes.infra.controllers.request;

import com.api.catalogo.filmes.app.models.ITypeMovie;

public enum TypeMovieRequest implements ITypeMovie {

    UPCOMING("upcoming"), LATEST("latest"), NOW_PLAYING("now_playing"),POPULAR("popular"), TOP_RATED("top_rated");

    private final String type;

    TypeMovieRequest(final String type){
        this.type = type;
    }

    @Override
    public String getType() {
        return type;
    }


}
