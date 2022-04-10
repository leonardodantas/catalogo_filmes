package com.api.catalogo.filmes.domain.models.details;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieDetailDTO {

    @JsonProperty("id")
    private int id;
    @JsonProperty("adult")
    private boolean adulto;
    @JsonProperty("backdrop_path")
    private String caminhoDaImagem;
    @JsonProperty("budget")
    private double despesas;
    @JsonProperty("genres")
    private List<GenderDetail> generos;
    @JsonProperty("homepage")
    private String paginaInicial;
    @JsonProperty("imdb_id")
    private String idIMBD;
    @JsonProperty("original_language")
    private String idiomaOriginal;
    @JsonProperty("original_title")
    private String tituloOriginal;
    @JsonProperty("overview")
    private String resumo;
    @JsonProperty("popularity")
    private double populariedade;
    @JsonProperty("poster_path")
    private String caminhoDoPoster;
    @JsonProperty("production_companies")
    private List<ProductionCities> cidadesDeProducoes;
    @JsonProperty("production_countries")
    private List<ProductionCountries> paisesDeProducoes;
    @JsonProperty("release_date")
    private String dataDeLancamento;
    @JsonProperty("revenue")
    private double receita;
    @JsonProperty("runtime")
    private String duracao;
    @JsonProperty("spoken_languages")
    private List<Language> idiomas;
    @JsonProperty("status")
    private String status;
    @JsonProperty("tagline")
    private String slogan;
    @JsonProperty("title")
    private String titulo;
    @JsonProperty("video")
    private boolean video;
    @JsonProperty("vote_average")
    private double mediaVotacao;
    @JsonProperty("vote_count")
    private int quantidadeVotos;
}
