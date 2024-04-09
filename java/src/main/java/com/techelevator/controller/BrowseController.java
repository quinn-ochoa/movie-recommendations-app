package com.techelevator.controller;

import com.techelevator.dao.GenresDao;
import com.techelevator.dao.MovieDao;
import com.techelevator.dao.UsersGenresDao;
import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;
import com.techelevator.services.TMDBService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class BrowseController {

    //properties
    UsersGenresDao usersGenresDao;
    GenresDao genresDao;

    MovieDao movieDao;

    //constructors
    public BrowseController(UsersGenresDao usersGenresDao, GenresDao genresDao, MovieDao movieDao) {
        this.usersGenresDao = usersGenresDao;
        this.genresDao = genresDao;
        this.movieDao = movieDao;
    }

    //methods
    @RequestMapping(path = "/user/{id}/recommended/", method = RequestMethod.GET)
    public Map<String, MovieApiResponse> getRecommendedMoviesByUserId(@PathVariable int id) {

        Map<String, Boolean> usersFavoriteGenres = usersGenresDao.getGenresByUserId(id);
        List<Integer> usersFavoriteGenreCodes = new ArrayList<>();
        TMDBService tmdbService = new TMDBService();
        double vote_average = 8.7;
        double vote_count = 25000;
        MovieApiResponse movieApiResponse = new MovieApiResponse();
        MovieApiResponse recommended;
        Map<String, MovieApiResponse> browser = new HashMap<>();

        for (Map.Entry<String, Boolean> usersFavoriteGenre : usersFavoriteGenres.entrySet()) {

            if (usersFavoriteGenre.getValue()) {

                usersFavoriteGenreCodes.add(genresDao.getGenreIdByGenreName(usersFavoriteGenre.getKey()));

            }

        } if (usersFavoriteGenreCodes.size() > 1) {

            recommended = tmdbService.queryForRecommended4u(movieApiResponse, usersFavoriteGenreCodes, vote_average, vote_count, 0, 0);
            browser.put("recommended4u", recommended);

        }

        for (Integer genre : usersFavoriteGenreCodes) {

            recommended = tmdbService.queryForGenreRecommendations(movieApiResponse, genre, vote_average, vote_count, 0, 0);
            browser.put(genresDao.getGenreNameById(genre), recommended);

        } recommended = tmdbService.queryForPopular();
        browser.put("popular", recommended);
        recommended = tmdbService.queryForAllTimeGreats(movieApiResponse ,vote_average, vote_count, 0 ,0);
        browser.put("allTimeGreats", recommended);

        for (Map.Entry<String, MovieApiResponse> result : browser.entrySet()) {

            for (Movie movie : result.getValue().getResults()) {

                if (!movieDao.isMovieInDatabase(movie.getId())) {

                    movieDao.addMovie(movie);

                }

            } movieDao.addGenreNameToResponse(result.getValue());

        } return browser;

    }

}
