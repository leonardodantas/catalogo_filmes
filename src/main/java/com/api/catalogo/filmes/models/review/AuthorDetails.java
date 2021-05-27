package com.api.catalogo.filmes.models.review;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorDetails {

    @JsonProperty(value = "name")
    private String nome;

    @JsonProperty(value = "username")
    private String nomeUsuario;

    @JsonProperty(value = "avatar_path")
    private String avatar;

    @JsonProperty(value = "rating")
    private String avaliacao;

}
