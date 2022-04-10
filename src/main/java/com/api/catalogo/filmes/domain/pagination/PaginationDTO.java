package com.api.catalogo.filmes.domain.pagination;

import lombok.Getter;

import java.util.List;

@Getter
public class PaginationDTO<T extends IPagination> {
    private int page;
    private List<T> results;
    private int total_pages;
    private int total_results;
}
