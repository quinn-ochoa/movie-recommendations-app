package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {

    //properties
    List<Integer> genre_ids;
    List<String> genre_names = new ArrayList<>();
    Integer id;
    String title;
    String overview;
    String poster_path;
    BigDecimal vote_average;
    int certification_id;
    boolean badName = false;

    //constructors
    public Movie() {
    }
    public Movie(List<Integer> genre_ids, List<String> genre_names, Integer id, String title, String overview, String poster_path, BigDecimal vote_average, int certification_id, boolean badName) {
       this.genre_ids = genre_ids;
       this.genre_names = genre_names;
       this.id = id;
       this.title = title;
       this.overview = overview;
       this.poster_path = poster_path;
       this.vote_average = vote_average;
       this.certification_id = certification_id;
       this.badName = badName;
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

    public void setGenre_names(List<String> genre_names) {
        this.genre_names = genre_names;
    }

    public List<String> getGenre_names() {
        return genre_names;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setVote_average(BigDecimal vote_average) {
        this.vote_average = vote_average;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCertification_id(int certification_id) {
        this.certification_id = certification_id;
    }

    public int getCertification_id() {
        return certification_id;
    }

    public void setBadName(boolean badName) {
        this.badName = badName;
    }

    public boolean isBadName() {
        return badName;
    }

    //methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return id.equals(movie.getId());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + genre_ids.hashCode();
        result = 31 * result + genre_names.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + overview.hashCode();
        result = 31 * result + poster_path.hashCode();
        result = 31 * result + vote_average.hashCode();
        return result;
    }

}
