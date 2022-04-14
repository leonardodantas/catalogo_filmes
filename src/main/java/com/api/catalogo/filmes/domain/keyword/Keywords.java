package com.api.catalogo.filmes.domain.keyword;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Keywords {
    private String id;
    private List<Keyword> keywords;
}
