package com.techelevator.controller;

import com.techelevator.dao.MovieDao;
import com.techelevator.dao.MovieGenreDao;
import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;
import com.techelevator.services.TMDBService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SearchController {
    //properties
    MovieDao movieDao;
    MovieGenreDao movieGenreDao;

    //constructors
    public SearchController(MovieDao movieDao, MovieGenreDao movieGenreDao) {
        this.movieDao = movieDao;
        this.movieGenreDao = movieGenreDao;
    }

    //methods
    @RequestMapping(path = "/search/{term}/", method = RequestMethod.GET)
    public MovieApiResponse getMoviesBySearchTerm(@PathVariable String term) {

        TMDBService tmdbService = new TMDBService(movieDao);
        MovieApiResponse movieApiResponse = tmdbService.getMoviesByTitle(term);

        for (Movie movie: movieApiResponse.getResults()) {

            if (!movieDao.isMovieInDatabase(movie.getId())) {

                movieDao.addMovie(movie);

            }

        } movieDao.addGenreNameToResponse(movieApiResponse);

        return movieApiResponse;

    }

    @RequestMapping(path = "/movie/{movie_id}/", method = RequestMethod.GET)
    public Movie getMovieByMovieId(@PathVariable int movie_id) {

        Movie movie = movieDao.getMovieById(movie_id);
        movie.setGenre_ids(movieGenreDao.getGenreIdsByMovieId(movie_id));
        movie = movieDao.addGenreNameToMovie(movie);
        movie.setId(movie_id);
        return movie;

    }

}
