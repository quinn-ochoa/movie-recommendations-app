package com.techelevator.dao;

import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;

import java.util.ArrayList;
import java.util.List;


public interface MovieDao {

    boolean isMovieInDatabase(int movieId);

    void addMovie(Movie movie);

    MovieApiResponse addGenreNameToResponse(MovieApiResponse movieApiResponse);

}
