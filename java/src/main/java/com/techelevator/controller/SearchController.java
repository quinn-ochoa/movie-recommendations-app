package com.techelevator.controller;

import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;
import com.techelevator.services.TMDBService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class SearchController {
    //properties

    //constructors
    public SearchController() {
    }

    //methods
    @RequestMapping(path = "/search/{term}")
    public MovieApiResponse getMoviesBySearchTerm(@PathVariable String term) {

        TMDBService tmdbService = new TMDBService();
        MovieApiResponse movieApiResponse = tmdbService.getMoviesByTitle(term);
        return movieApiResponse;

    }

}
