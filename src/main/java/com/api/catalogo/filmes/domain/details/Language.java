package com.api.catalogo.filmes.domain.details;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Language {
    private String english_name;
    private String iso_639_1;
    private String name;
}
