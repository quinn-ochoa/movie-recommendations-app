//package com.techelevator.services;
//
//import com.techelevator.dao.MovieDao;
//import com.techelevator.dao.MovieGenreDao;
//import com.techelevator.model.Movie;
//import com.techelevator.model.MovieApiResponse;
//
//public class SearchService {
//    MovieDao movieDao;
//    MovieGenreDao movieGenreDao;
//    Movie
//    public MovieApiResponse getMoviesBySearchTerm(String term) {
//        TMDB_APIService tmdbApiService = new TMDB_APIService();
//        ProfanityFilterAPIService profanityFilterAPIService = new ProfanityFilterAPIService();
//        int page = 1;
//        MovieApiResponse movieApiResponse = new MovieApiResponse();
//        MovieApiResponse goodResults = new MovieApiResponse();
//
//        while ((page == 1 || page<= movieApiResponse.getTotal_pages()) && goodResults.getResults().size() < 20) {
//
//            movieApiResponse = tmdbApiService.getMoviesByTitle(term, page);
//
//            for (Movie movie : movieApiResponse.getResults()) {
//
//                if(!movieDao.isMovieInDatabase(movie.getId())) {
//
//                    movieDao.addMovie(movie);
//                    movie.setCertification_id(tmdbApiService.queryForCertificationById(movie.getId()));
//                    movieGenreDao.updateMovieGenreAssociation(movie.getGenre_ids(), movie.getId());
//                    movieCertificationDao.updateMovieCertificationAssociation(movie.getCertification_id(), movie.getId());
//
//                } else {
//
//                    movie.setCertification_id(movieCertificationDao.getCertificationByMovieId(movie.getId()));
//
//                }
//
//                if (movie.getCertification_id() > 0) {
//
//                    goodResults.getResults().add(movie);
//
//                    if (goodResults.getResults().size() >= 20) {
//
//                        break;
//
//                    }
//
//                }
//
//            } page++;
//
//        } goodResults = movieDao.addGenreNameToResponse(goodResults);
//        return goodResults;
//
//    }
//}
