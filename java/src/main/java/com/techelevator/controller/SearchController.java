package com.techelevator.controller;

import com.techelevator.dao.JdbcMovieDao;
import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;
import com.techelevator.services.TMDBService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SearchController {
    //properties
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    JdbcMovieDao jdbcMovieDao = new JdbcMovieDao(jdbcTemplate);

    //constructors
    public SearchController() {
    }

    //methods
    @RequestMapping(path = "/search/{term}", method = RequestMethod.GET)
    public MovieApiResponse getMoviesBySearchTerm(@PathVariable String term) {

        TMDBService tmdbService = new TMDBService();
        MovieApiResponse movieApiResponse = tmdbService.getMoviesByTitle(term);
//TODO test once Database is working
//        for (Movie movie: movieApiResponse.getResults()) {
//
//            jdbcMovieDao.isMovieInDatabase(movie.getId());
//
//        }
            return movieApiResponse;

    }

}
