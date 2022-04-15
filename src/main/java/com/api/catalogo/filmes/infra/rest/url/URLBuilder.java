package com.api.catalogo.filmes.infra.rest.url;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.app.models.ITypeMovie;

import java.util.Objects;

public class URLBuilder {

    private final String baseUrl;

    private URLBuilder(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getValue() {
        return this.baseUrl;
    }

    public static class Builder {
        private static final String URL_API = "https://api.themoviedb.org/3/movie/";
        private final String apiKey;
        private ITypeMovie typeRequest;
        private int movieId;
        private int page;
        private ILanguageMovie language;
        private Resource resource;

        public Builder(final String apiKey) {
            this.apiKey = apiKey;
        }

        public Builder typeMovie(final ITypeMovie typeRequest) {
            this.typeRequest = typeRequest;
            return this;
        }

        public Builder movieId(final int movieId) {
            this.movieId = movieId;
            return this;
        }

        public Builder page(final int page) {
            this.page = page;
            return this;
        }

        public Builder language(final ILanguageMovie language) {
            this.language = language;
            return this;
        }

        public Builder resource(final Resource resource) {
            this.resource = resource;
            return this;
        }

        public URLBuilder builder() {
            final var url = this.generateURL();
            return new URLBuilder(url);
        }

        private String generateURL() {
            final var url = new StringBuilder();
            url.append(URL_API);

            url.append(getMovieIdAndResource(url));

            url.append("?api_key=");
            url.append(apiKey);

            if (page != 0) {
                url.append("&page=").append(page);
            }

            if (!Objects.isNull(language)) {
                url.append("&language=").append(language.getLanguage());
            }

            return url.toString();
        }

        private String getMovieIdAndResource(StringBuilder url) {
            if (!Objects.isNull(typeRequest)) {
                return typeRequest.getType();
            }
            var movieAndResource = new StringBuilder();
            if (movieId != 0) {
                movieAndResource.append(movieId);
                if (!Objects.isNull(resource)) {
                    movieAndResource.append("/").append(resource.getResource());
                }
            }

            return movieAndResource.toString();
        }
    }
}
