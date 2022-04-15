package com.api.catalogo.filmes.infra.rest.url;

import com.api.catalogo.filmes.app.models.ILanguageMovie;
import com.api.catalogo.filmes.app.models.ITypeMovie;

import java.util.Objects;

public record URLBuilder(String value) {

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

            if (!Objects.isNull(typeRequest)) {
                url.append(typeRequest.getType());
            } else if (movieId != 0) {
                url.append(movieId);
                if (!Objects.isNull(resource)) {
                    url.append("/").append(resource.getResource());
                }
            }

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
    }
}
