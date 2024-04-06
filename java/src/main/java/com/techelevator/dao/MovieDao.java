package com.techelevator.dao;

import com.techelevator.model.Movie;

public interface MovieDao {

    boolean isMovieInDatabase(int movieId);

    void addMovie(Movie movie);
}
