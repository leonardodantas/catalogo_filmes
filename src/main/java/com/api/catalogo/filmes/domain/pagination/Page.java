package com.api.catalogo.filmes.domain.pagination;

import lombok.Getter;

import java.util.List;

@Getter
public class Page<T> {
    private int page;
    private List<T> results;
    private int total_pages;
    private int total_results;

    public Page(int page, List<T> results, int total_pages, int total_results) {
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }
}
