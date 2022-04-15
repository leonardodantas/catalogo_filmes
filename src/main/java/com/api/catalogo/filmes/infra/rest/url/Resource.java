package com.api.catalogo.filmes.infra.rest.url;

public enum Resource {

    SIMILAR, REVIEWS, KEYWORDS, VIDEOS;

    public String getResource() {
        return this.name().toLowerCase();
    }
}
