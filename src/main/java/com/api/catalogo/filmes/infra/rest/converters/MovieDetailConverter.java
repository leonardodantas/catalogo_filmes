package com.api.catalogo.filmes.infra.rest.converters;

import com.api.catalogo.filmes.domain.details.MovieDetail;
import com.api.catalogo.filmes.infra.rest.jsons.MovieDetailRest;

public class MovieDetailConverter {
    private final static String URL_BASE = "https://www.themoviedb.org/t/p/w220_and_h330_face";

    public static MovieDetail convert(MovieDetailRest movieDetailRest) {
        return MovieDetail.builder()
                .id(movieDetailRest.id())
                .adult(movieDetailRest.adult())
                .original_language(movieDetailRest.original_language())
                .original_title(movieDetailRest.original_title())
                .popularity(movieDetailRest.popularity())
                .overview(movieDetailRest.overview())
                .release_date(movieDetailRest.release_date())
                .budget(movieDetailRest.budget())
                .genres(movieDetailRest.genres().stream().map(GenderDetailConverter::convert).toList())
                .homepage(movieDetailRest.homepage())
                .imdb_id(movieDetailRest.imdb_id())
                .production_companies(movieDetailRest.production_companies().stream().map(ProductionCitiesConverter::convert).toList())
                .production_countries(movieDetailRest.production_countries().stream().map(ProductionCountriesConverter::convert).toList())
                .revenue(movieDetailRest.revenue())
                .runtime(movieDetailRest.runtime())
                .spoken_languages(movieDetailRest.spoken_languages().stream().map(LanguageConverter::convert).toList())
                .status(movieDetailRest.status())
                .tagline(movieDetailRest.tagline())
                .title(movieDetailRest.title())
                .vote_average(movieDetailRest.vote_average())
                .vote_count(movieDetailRest.vote_count())
                .poster_path(GenerateURL.getURLTMDB(movieDetailRest.poster_path()))
                .backdrop_path(GenerateURL.getURLTMDB(movieDetailRest.backdrop_path()))
                .video(movieDetailRest.video())
                .build();
    }
}
