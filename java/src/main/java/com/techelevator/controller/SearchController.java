package com.techelevator.controller;

import com.techelevator.dao.MovieCertificationDao;
import com.techelevator.dao.MovieDao;
import com.techelevator.dao.MovieGenreDao;
import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;
import com.techelevator.services.ProfanityFilterAPIService;
import com.techelevator.services.TMDB_APIService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class SearchController {
    //properties
    MovieDao movieDao;
    MovieGenreDao movieGenreDao;
    MovieCertificationDao movieCertificationDao;

    //constructors
    public SearchController(MovieDao movieDao, MovieGenreDao movieGenreDao, MovieCertificationDao movieCertificationDao) {
        this.movieDao = movieDao;
        this.movieGenreDao = movieGenreDao;
        this.movieCertificationDao = movieCertificationDao;
    }

    //methods
    @RequestMapping(path = "/search/{term}/", method = RequestMethod.GET)
    public MovieApiResponse getMoviesBySearchTerm(@PathVariable String term) {
        TMDB_APIService tmdbApiService = new TMDB_APIService();
        ProfanityFilterAPIService profanityFilterAPIService = new ProfanityFilterAPIService();
        int page = 1;
        MovieApiResponse movieApiResponse = new MovieApiResponse();
        MovieApiResponse goodResults = new MovieApiResponse();

        while ((page == 1 || page<= movieApiResponse.getTotal_pages()) && goodResults.getResults().size() < 20) {

            movieApiResponse = tmdbApiService.getMoviesByTitle(term, page);

            for (Movie movie : movieApiResponse.getResults()) {

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

            } page++;

        } goodResults = movieDao.addGenreNameToResponse(goodResults);
        return goodResults;

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
