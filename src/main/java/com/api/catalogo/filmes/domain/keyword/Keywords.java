package com.api.catalogo.filmes.domain.keyword;

import lombok.Getter;

import java.util.List;

@Getter
public class Keywords {

    private int id;
    private List<Keyword> keywords;
}
