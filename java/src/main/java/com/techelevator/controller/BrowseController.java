package com.techelevator.controller;

import com.techelevator.dao.*;
import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;
import com.techelevator.services.TMDB_APIService;
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
    MovieGenreDao movieGenreDao;
    MovieCertificationDao movieCertificationDao;


    //constructors
    public BrowseController(UsersGenresDao usersGenresDao, GenresDao genresDao, MovieDao movieDao, MovieGenreDao movieGenreDao, MovieCertificationDao movieCertificationDao) {
        this.usersGenresDao = usersGenresDao;
        this.genresDao = genresDao;
        this.movieDao = movieDao;
        this.movieGenreDao = movieGenreDao;
        this.movieCertificationDao = movieCertificationDao;
    }

    //methods
    @RequestMapping(path = "/user/{id}/recommended/", method = RequestMethod.GET)
    public Map<String, MovieApiResponse> getRecommendedMoviesByUserId(@PathVariable int id) {
        //TODO NEW CODE
        Map<String, Boolean> usersFavoriteGenres = usersGenresDao.getGenresByUserId(id);
        List<Integer> usersFavoriteGenreCodes = new ArrayList<>();
        TMDB_APIService tmdbService = new TMDB_APIService();
        double vote_average = 8;
        double vote_count = 10000;
        MovieApiResponse movieApiResponse = new MovieApiResponse();
        MovieApiResponse recommended;
        Map<String, MovieApiResponse> browser = new HashMap<>();

        for (Map.Entry<String, Boolean> usersFavoriteGenre : usersFavoriteGenres.entrySet()) {

            if (usersFavoriteGenre.getValue()) {

                usersFavoriteGenreCodes.add(genresDao.getGenreIdByGenreName(usersFavoriteGenre.getKey()));

            }

        } if (usersFavoriteGenreCodes.size() > 1) {

            recommended = tmdbService.queryForRecommended4u(movieApiResponse, usersFavoriteGenreCodes, vote_average, vote_count, 0, 0);
            recommended = filterAndTrim(recommended);
            browser.put("recommended4u", recommended);

        } for (Integer genre : usersFavoriteGenreCodes) {

            recommended = tmdbService.queryForGenreRecommendations(movieApiResponse, genre, vote_average, vote_count, 0, 0);
            recommended = filterAndTrim(recommended);
            browser.put(genresDao.getGenreNameById(genre), recommended);

        } recommended = tmdbService.queryForPopular();
        recommended = filterAndTrim(recommended);
        browser.put("popular", recommended);
        recommended = tmdbService.queryForAllTimeGreats(movieApiResponse ,vote_average, vote_count, 0 ,0);
        recommended = filterAndTrim(recommended);
        browser.put("allTimeGreats", recommended);

        for (Map.Entry<String, MovieApiResponse> result : browser.entrySet()) {

             movieDao.addGenreNameToResponse(result.getValue());

        } return browser;

    }

    private MovieApiResponse filterAndTrim(MovieApiResponse recommended) {

        TMDB_APIService tmdbApiService = new TMDB_APIService();
        MovieApiResponse goodResults = new MovieApiResponse();

        for (Movie movie : recommended.getResults()) {

            if(!movieDao.isMovieInDatabase(movie.getId())) {

                movieDao.addMovie(movie);
                movie.setCertification_id(tmdbApiService.queryForCertificationById(movie.getId()));
                movieGenreDao.updateMovieGenreAssociation(movie.getGenre_ids(), movie.getId());
                movieCertificationDao.updateMovieCertificationAssociation(movie.getCertification_id(), movie.getId());

            } else {

                movie.setCertification_id(movieCertificationDao.getCertificationByMovieId(movie.getId()));

            }

            if (movie.getCertification_id() > 0 && movie.getCertification_id() < 5 && movie.getPoster_path() != null) {

                goodResults.getResults().add(movie);

                if (goodResults.getResults().size() >= 20) {

                    break;

                }

            }

        } return goodResults;

    }

    @RequestMapping(path = "/recommended/", method = RequestMethod.GET)
    public Map<String, MovieApiResponse> getRecommendedForAllGenres() {

        Map<String, Boolean> allGenres = usersGenresDao.getGenresByUserId(1);
        List<Integer> allGenreCodes = new ArrayList<>();
        TMDB_APIService tmdbService = new TMDB_APIService();
        double vote_average = 8.7;
        double vote_count = 25000;
        MovieApiResponse movieApiResponse = new MovieApiResponse();
        MovieApiResponse recommended;
        Map<String, MovieApiResponse> browser = new HashMap<>();

        for (Map.Entry<String, Boolean> usersFavoriteGenre : allGenres.entrySet()) {

            allGenreCodes.add(genresDao.getGenreIdByGenreName(usersFavoriteGenre.getKey()));

        } for (Integer genre : allGenreCodes) {

            recommended = tmdbService.queryForGenreRecommendations(movieApiResponse, genre, vote_average, vote_count, 0, 0);
            recommended = filterAndTrim(recommended);
            browser.put(genresDao.getGenreNameById(genre), recommended);

        } for (Map.Entry<String, MovieApiResponse> result : browser.entrySet()) {

            movieDao.addGenreNameToResponse(result.getValue());

        } return browser;

    }

}
