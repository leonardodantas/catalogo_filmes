package com.api.catalogo.filmes.domain.review;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AuthorDetails {
    private String name;
    private String username;
    private String avatar_path;
    private String rating;

}
