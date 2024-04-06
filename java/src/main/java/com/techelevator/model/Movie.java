package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    //properties
    List<Integer> genre_ids;
    int id;
    String title;
    String overview;
    String poster_path;
    BigDecimal vote_average;

    //constructors
    public Movie() {

    }
    public Movie(List<Integer> genre_ids, int id, String title, String overview, String poster_path, BigDecimal vote_average) {
       this.genre_ids = genre_ids;
       this.id = id;
       this.title = title;
       this.overview = overview;
       this.poster_path = poster_path;
       this.vote_average = vote_average;
    }

    //getters & setter
    public int getId() {
        return id;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public BigDecimal getVote_average() {
        return vote_average;
    }
}
