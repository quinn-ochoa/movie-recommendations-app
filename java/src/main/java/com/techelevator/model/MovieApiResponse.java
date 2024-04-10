package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

public class MovieApiResponse {
    //properties
    private List<Movie> results = new ArrayList<>();

    //getters & setters
    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

}
