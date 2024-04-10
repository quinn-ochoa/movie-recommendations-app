package com.techelevator.controller;

import com.techelevator.dao.MovieDao;
import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;
import com.techelevator.services.TMDBService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SearchController {
    //properties
    MovieDao movieDao;

    //constructors
    public SearchController(MovieDao movieDao) {
        this.movieDao = movieDao;
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

}
