package com.techelevator.dao;

import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;

public interface MovieDao {

    boolean isMovieInDatabase(int movieId);

    void addMovie(Movie movie);

    MovieApiResponse addGenreNameToResponse(MovieApiResponse movieApiResponse);

    MovieApiResponse throwOutBadMovies(MovieApiResponse movieApiResponse);

    Movie getMovieById(int movie_id);

}
