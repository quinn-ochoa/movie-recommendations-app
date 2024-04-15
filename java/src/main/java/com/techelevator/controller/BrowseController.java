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
    UsersInfoDao usersInfoDao;


    //constructors
    public BrowseController(UsersGenresDao usersGenresDao, GenresDao genresDao, MovieDao movieDao, MovieGenreDao movieGenreDao, MovieCertificationDao movieCertificationDao, UsersInfoDao usersInfoDao) {
        this.usersGenresDao = usersGenresDao;
        this.genresDao = genresDao;
        this.movieDao = movieDao;
        this.movieGenreDao = movieGenreDao;
        this.movieCertificationDao = movieCertificationDao;
        this.usersInfoDao = usersInfoDao;
    }

    //methods
    @RequestMapping(path = "/user/{id}/recommended/", method = RequestMethod.GET)
    public Map<String, MovieApiResponse> getRecommendedMoviesByUserId(@PathVariable int id) {

        Map<String, Boolean> usersFavoriteGenres = usersGenresDao.getGenresByUserId(id);
        List<Integer> usersFavoriteGenreCodes = new ArrayList<>();
        TMDB_APIService tmdbService = new TMDB_APIService();
        MovieApiResponse recommended = new MovieApiResponse();
        MovieApiResponse moviesAlreadyReturned = new MovieApiResponse();
        Map<String, MovieApiResponse> browser = new HashMap<>();
        //get user favorite genres and converts it to code readable by the api
        for (Map.Entry<String, Boolean> usersFavoriteGenre : usersFavoriteGenres.entrySet()) {

            if (usersFavoriteGenre.getValue()) {

                usersFavoriteGenreCodes.add(genresDao.getGenreIdByGenreName(usersFavoriteGenre.getKey()));

            }
        //only gets Recommended4u if user has more than one favorite genre
        }
        while (usersFavoriteGenreCodes.size() > 1) {

            while (recommended.getResults().size() < 20) {
                //gets 50 movies, filters out unwanted, reduces to 20, and adds them to the browser return map
                MovieApiResponse moviesReturned = tmdbService.queryForRecommended4u(moviesAlreadyReturned, usersFavoriteGenreCodes, usersInfoDao.getAppropriateCertification(id));

                for (Movie movie : moviesReturned.getResults()) {

                    moviesAlreadyReturned.getResults().add(movie);
                    recommended.getResults().add(movie);

                }
                recommended = filterAndTrim(recommended, id);

            } browser.put("recommended4u", recommended);
            moviesAlreadyReturned = new MovieApiResponse();
            recommended = new MovieApiResponse();

        }
        //get recommended genre lists
        for (Integer genre : usersFavoriteGenreCodes) {

            while (recommended.getResults().size() < 20) {

                MovieApiResponse moviesReturned = tmdbService.queryForGenreRecommendations(moviesAlreadyReturned, genre, usersInfoDao.getAppropriateCertification(id));

                for (Movie movie : moviesReturned.getResults()) {

                    moviesAlreadyReturned.getResults().add(movie);
                    recommended.getResults().add(movie);

                } recommended = filterAndTrim(recommended, id);

            } browser.put(genresDao.getGenreNameById(genre), recommended);
            moviesAlreadyReturned = new MovieApiResponse();
            recommended = new MovieApiResponse();
        //get popular movies
        }
        while (recommended.getResults().size() < 20) {

            MovieApiResponse moviesReturned = tmdbService.queryForPopular(moviesAlreadyReturned ,usersInfoDao.getAppropriateCertification(id));

            for (Movie movie : moviesReturned.getResults()) {

                moviesAlreadyReturned.getResults().add(movie);
                recommended.getResults().add(movie);

            } recommended = filterAndTrim(recommended, id);

        } browser.put("popular", recommended);
        moviesAlreadyReturned = new MovieApiResponse();
        recommended = new MovieApiResponse();

        while (recommended.getResults().size() < 20) {

            MovieApiResponse moviesReturned = tmdbService.queryForAllTimeGreats(moviesAlreadyReturned, usersInfoDao.getAppropriateCertification(id));

            for (Movie movie : moviesReturned.getResults()) {

                moviesAlreadyReturned.getResults().add(movie);
                recommended.getResults().add(movie);

            } recommended = filterAndTrim(recommended, id);

        } browser.put("allTimeGreats", recommended);
        MovieApiResponse moviesReturned = movieDao.getFavoriteMovies(id);

        for (Movie movie : moviesReturned.getResults()) {

            movie.setGenre_ids(movieGenreDao.getGenreIdsByMovieId(movie.getId()));

        } browser.put("favorites", moviesReturned);

        for (Map.Entry<String, MovieApiResponse> result : browser.entrySet()) {

             movieDao.addGenreNameToResponse(result.getValue());

        } return browser;

    }

    private MovieApiResponse filterAndTrim(MovieApiResponse recommended, int user_id) {

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

            } if (movie.getCertification_id() > 0 && movie.getCertification_id() <= usersInfoDao.getAppropriateCertification(user_id) && movie.getPoster_path() != null && !movie.isBadName()) {

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

            recommended = tmdbService.queryForGenreRecommendations(movieApiResponse, genre, 4);
            recommended = filterAndTrim(recommended, 1);
            browser.put(genresDao.getGenreNameById(genre), recommended);

        } for (Map.Entry<String, MovieApiResponse> result : browser.entrySet()) {

            movieDao.addGenreNameToResponse(result.getValue());

        } return browser;

    }

}
