package com.api.catalogo.filmes.domain.details;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
public class MovieDetail {

    private int id;
    private boolean adult;
    private String backdrop_path;
    private double budget;
    private List<GenderDetail> genres;
    private String homepage;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private List<ProductionCities> production_companies;
    private List<ProductionCountries> production_countries;
    private String release_date;
    private double revenue;
    private String runtime;
    private List<Language> spoken_languages;
    private String status;
    private String tagline;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;

}
