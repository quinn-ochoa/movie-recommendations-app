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
    @RequestMapping(path = "/movie/{id}", method = RequestMethod.GET)
    public Movie getMovieById(@PathVariable int id) {
        return movieDao.getMovieById(id);
    }



    @RequestMapping(path = "", method =RequestMethod.GET)
    public MovieApiResponse getMovieByID(@PathVariable int id){


            if (!movieDao.isMovieInDatabase(movie.getId())) {

                movieDao.addMovie(movie);

            }


        return movieApiResponse;

    }

}
