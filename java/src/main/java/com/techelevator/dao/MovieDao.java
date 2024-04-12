package com.techelevator.dao;

import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;

public interface MovieDao {

    boolean isMovieInDatabase(int movieId);

    void addMovie(Movie movie);

    MovieApiResponse addGenreNameToResponse(MovieApiResponse movieApiResponse);

    MovieApiResponse throwOutBadMovies(MovieApiResponse movieApiResponse);

<<<<<<< HEAD
    Movie getMovieById(int movie_id);

    Movie addGenreNameToMovie(Movie movie);

    void markMovieAsDoNotShow(int movie_id);
=======
>>>>>>> 6e6c8149c836380e2e2e6ba1e837dba6117da87a

}
