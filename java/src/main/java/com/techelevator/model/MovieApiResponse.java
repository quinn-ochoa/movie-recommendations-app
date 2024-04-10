package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class MovieApiResponse {
    //properties
    private List<Movie> results = new ArrayList<>();
    private int total_pages = 1;

    //getters & setters
    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

}
