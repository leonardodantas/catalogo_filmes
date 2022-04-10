package com.api.catalogo.filmes.app.utils.tmdb;

public enum Language {

    PT("&language=pt-BR"),EN("&language=en-EN"),ES("&language=es-ES");

    private String language;

    Language(String language){
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
