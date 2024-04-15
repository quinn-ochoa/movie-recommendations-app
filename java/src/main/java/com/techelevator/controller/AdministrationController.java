package com.techelevator.controller;

import com.techelevator.dao.MovieDao;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class AdministrationController {
    //properties
    MovieDao movieDao;

    //constructors
    public AdministrationController(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    //methods
    @RequestMapping(path = "/movie/noShow/{movie_id}/", method = RequestMethod.PUT)
    public void doNotShowMovie(@Valid @PathVariable int movie_id) {

        movieDao.markMovieAsDoNotShow(movie_id);

    }

}
