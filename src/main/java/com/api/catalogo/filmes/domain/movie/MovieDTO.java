package com.api.catalogo.filmes.domain.movie;

import com.api.catalogo.filmes.domain.pagination.IPagination;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class MovieDTO implements IPagination {

    private final String URL_BASE = "https://www.themoviedb.org/t/p/w220_and_h330_face";

    private int id;
    private boolean adult;
    @Setter
    private String backdrop_path;
    private List<Integer> genre_ids;
    private String original_language;
    private String title;
    private String original_title;
    private String overview;
    private double popularity;
    @Setter
    private String poster_path;
    private String release_date;
    private double vote_average;
    private int vote_count;
    private boolean video;

    public String getPoster_path(){
        return URL_BASE.concat(this.poster_path);
    }

    public String getBackdrop_path(){
        return URL_BASE.concat(this.backdrop_path);
    }

}
