package com.techelevator.dao;

import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;

import java.util.List;

public interface MovieDao {

    boolean isMovieInDatabase(int movieId);

    void addMovie(Movie movie);

    MovieApiResponse addGenreNameToResponse(MovieApiResponse movieApiResponse);

    Movie getMovieById(int movie_id);

    Movie addGenreNameToMovie(Movie movie);

    void markMovieAsDoNotShow(int movie_id);

    MovieApiResponse getFavoriteMovies(int id);

    List<Movie> getAllMoviesReviewedByUser(int userId);
}
