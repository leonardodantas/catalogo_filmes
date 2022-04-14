package com.api.catalogo.filmes.domain.movie;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Movie {

    private int id;
    private boolean adult;
    private String backdrop_path;
    private List<Integer> genre_ids;
    private String original_language;
    private String title;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private double vote_average;
    private int vote_count;
    private boolean video;


}
