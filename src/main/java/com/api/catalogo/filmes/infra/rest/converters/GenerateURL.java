package com.api.catalogo.filmes.infra.rest.converters;

import com.google.common.base.Strings;

public class GenerateURL {
    private final static String URL_TMDB = "https://www.themoviedb.org/t/p/w220_and_h330_face";
    private final static String URL_YOUTUBE = "https://www.youtube.com/watch?v=";

    public static String getURLTMDB(String path) {
        if (Strings.isNullOrEmpty(path)) {
            return "";
        }
        return URL_TMDB + path;
    }

    public static String getURLYoutube(final String site, final String key) {
        return "YouTube".equals(site) ? URL_YOUTUBE.concat(key) : "";
    }
}
