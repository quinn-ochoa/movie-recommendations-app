package com.techelevator.services;

import com.techelevator.dao.MovieDao;
import com.techelevator.model.*;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TMDB_APIService {
    //properties
    private final String API_BASE_URL = "https://api.themoviedb.org/3/";
    private final String SEARCH = "search/movie?query=";
    private final String DISCOVER = "discover/movie?include_adult=false&include_video=false&language=en-US&page=";
    private RestTemplate restTemplate = new RestTemplate();
    private final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZGU3MzhlZTFmNjUzNGQ3MDFlYjBlZDcwYjBhMDdmNCIsInN1YiI6IjY1ZGJlMjMxZWQyYWMyMDE4NzQwZGQyNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.SrPF0blfo7MklOWQqkeSN8WnfNLQgyUS8r0TtSOdAC4";
    MovieDao movieDao;

    //constructors

    //methods
    public MovieApiResponse getMoviesByTitle(String searchTerm, int page) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        HttpEntity<MovieApiResponse> entity = new HttpEntity<>(headers);
        MovieApiResponse movieApiResponse;
        String formattedSearchTerm = searchTerm.replace(" ", "%20");

        try {

            ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + SEARCH + formattedSearchTerm + "&include_adult=false&page=" + page, HttpMethod.GET, entity, MovieApiResponse.class);
            movieApiResponse = response.getBody();

        } catch (RestClientException e) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

        } if (movieApiResponse == null) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No movies found for the given search term.");

        } return movieApiResponse;

    }

    public MovieApiResponse queryForRecommended4u(MovieApiResponse alreadyReturned, List<Integer> passedInGenres, int userAgeRating) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        MovieApiResponse moviesPulledFormApi = new MovieApiResponse();
        MovieApiResponse moviesToReturn = new MovieApiResponse();
        List<Integer> genres = new ArrayList<>();
        for (Integer genre : passedInGenres) {
            genres.add(genre);
        } double vote_average = 7;
        double vote_count = 10000;

        //do until 50 movies seleted
        while (moviesPulledFormApi.getResults().size() < 50) {

            for (int i = 0; i < genres.size(); i++) {

                Integer firstGenre = genres.get(i);
                genres.remove(i);

                for (int j = 0; j < genres.size(); j++) {

                    Integer secondGenre = genres.get(j);
                    String genresAsApiInput = firstGenre + "%2C" + secondGenre;
                    int page = 1;
                    //Do the thing

                    while (page == 1 || page <= moviesPulledFormApi.getTotal_pages()) {

                        try {

                            ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + DISCOVER + page + "&sort_by=popularity.desc&vote_average.gte=" + vote_average + "&vote_count.gte=" + vote_count + "&with_genres=" + genresAsApiInput, HttpMethod.GET, entity, MovieApiResponse.class);
                            moviesPulledFormApi = response.getBody();
                            page++;

                        } catch (RestClientException e) {

                            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

                        } catch (IndexOutOfBoundsException e) {

                        }
                        for (Movie movie : moviesPulledFormApi.getResults()) {

                            if (!moviesToReturn.getResults().contains(movie) && !alreadyReturned.getResults().contains(movie)) {

                                moviesToReturn.getResults().add(movie);

                                if (moviesToReturn.getResults().size() >= 50) {

                                    return moviesToReturn;

                                }

                            }

                        }

                    } vote_average *= .99;
                    vote_count *= .99;

                }
                genres.add(i, firstGenre);

            } for (int i = 0; i < genres.size(); i++) {

                Integer firstGenre = genres.get(i);
                String genresAsApiInput = firstGenre + "";
                int page = 1;

                while (page == 1 || page <= moviesPulledFormApi.getTotal_pages()) {

                    try {

                        ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + DISCOVER + page + "&sort_by=popularity.desc&vote_average.gte=" + vote_average + "&vote_count.gte=" + vote_count + "&with_genres=" + genresAsApiInput, HttpMethod.GET, entity, MovieApiResponse.class);
                        moviesPulledFormApi = response.getBody();
                        page++;

                    } catch (RestClientException e) {

                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

                    }
                    for (Movie movie : moviesPulledFormApi.getResults()) {

                        if (!moviesToReturn.getResults().contains(movie) && !alreadyReturned.getResults().contains(movie)) {

                            moviesToReturn.getResults().add(movie);

                            if (moviesToReturn.getResults().size() >= 50) {

                                return moviesToReturn;

                            }

                        }

                    }

                } vote_average *= .99;
                vote_count *= .99;

            } vote_average *= .9;
            vote_count *= .9;

        } return null;

    }

    public MovieApiResponse queryForGenreRecommendations(MovieApiResponse alreadyReturned, Integer genre, int userAgeRating) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        HttpEntity<MovieApiResponse> entity = new HttpEntity<>(headers);
        MovieApiResponse moviesPulledFromApi = new MovieApiResponse();
        MovieApiResponse moviesToReturn = new MovieApiResponse();
        double vote_average = 7.0;
        double vote_count = 10000;

        while (moviesToReturn.getResults().size() < 50) {

            int page = 1;

            while (page ==1 || page <= moviesPulledFromApi.getTotal_pages()) {

                try {

                    ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + DISCOVER + page + "&sort_by=popularity.desc&vote_average.gte=" + vote_average + "&vote_count.gte=" + vote_count + "&with_genres=" + genre, HttpMethod.GET, entity, MovieApiResponse.class);
                    moviesPulledFromApi = response.getBody();
                    page++;

                } catch (RestClientException e) {

                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

                } for (Movie movie : moviesPulledFromApi.getResults()) {

                    if (!moviesToReturn.getResults().contains(movie) && !alreadyReturned.getResults().contains(movie)) {

                        moviesToReturn.getResults().add(movie);

                        if (moviesToReturn.getResults().size() >= 50) {

                            return moviesToReturn;

                        }

                    }

                }

            } vote_average *= .9;
            vote_count *= .9;

        } return null;

    }

    public MovieApiResponse queryForPopular(MovieApiResponse alreadyReturned, int userAgeRating) {

        int page = 1;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        HttpEntity<MovieApiResponse> entity = new HttpEntity<>(headers);
        MovieApiResponse moviesPulledFromApi;
        MovieApiResponse moviesToReturn = new MovieApiResponse();

        while (moviesToReturn.getResults().size() < 50) {

            try {

                ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + DISCOVER + page + "&sort_by=popularity.desc", HttpMethod.GET, entity, MovieApiResponse.class);
                moviesPulledFromApi = response.getBody();

            } catch (RestClientException e) {

                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

            } for (Movie movie : moviesPulledFromApi.getResults()) {

                if (!moviesToReturn.getResults().contains(movie) && !alreadyReturned.getResults().contains(movie)) {

                    moviesToReturn.getResults().add(movie);

                    if (moviesToReturn.getResults().size() >= 50) {

                        return moviesToReturn;

                    }

                }

            } page++;

        } return null;

    }

    public MovieApiResponse queryForAllTimeGreats(MovieApiResponse alreadyReturned, int userAgeRating) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        HttpEntity<MovieApiResponse> entity = new HttpEntity<>(headers);
        MovieApiResponse moviesPulledFromApi = new MovieApiResponse();
        MovieApiResponse moviesToReturn = new MovieApiResponse();
        double vote_average = 8.7;
        double vote_count = 25000;

        while (moviesToReturn.getResults().size() < 50) {

            int page = 1;

            while (page == 1 || page <= moviesPulledFromApi.getTotal_pages()) {

                try {

                    ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + DISCOVER + page + "&sort_by=popularity.desc&vote_average.gte=" + vote_average + "&vote_count.gte=" + vote_count, HttpMethod.GET, entity, MovieApiResponse.class);
                    moviesPulledFromApi = response.getBody();

                } catch (RestClientException e) {

                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

                }
                for (Movie movie : moviesPulledFromApi.getResults()) {

                    if (!moviesToReturn.getResults().contains(movie) && !alreadyReturned.getResults().contains(movie)) {

                        moviesToReturn.getResults().add(movie);

                        if (moviesToReturn.getResults().size() >= 50) {

                            return moviesToReturn;

                        }

                    }

                } page++;

            } vote_average *= .9;
            vote_count *= .9;


        } return  moviesToReturn;

    }

    public int queryForCertificationById(int movie_id) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        HttpEntity<ReleaseDatesApiResponse> entity = new HttpEntity<>(headers);
        ReleaseDatesApiResponse releaseDatesApiResponse;

        try {

            ResponseEntity<ReleaseDatesApiResponse> response = restTemplate.exchange(API_BASE_URL + "/movie/" + movie_id + "/release_dates", HttpMethod.GET, entity, ReleaseDatesApiResponse.class);
            releaseDatesApiResponse = response.getBody();

        } catch (HttpClientErrorException.NotFound notFound) {

            return 0;

        } catch (RestClientException e) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

        } for (CountryReleaseDates country : releaseDatesApiResponse.getResults()) {

            if(country.getIso_3166_1().equalsIgnoreCase("US")) {

                for (ReleaseDates releaseDate : country.getRelease_dates()) {

                    if (releaseDate.getCertification() == null || releaseDate.getCertification().contains("NR")) {

                        return 0;

                    } else if (releaseDate.getCertification().equalsIgnoreCase("PG-13")) {

                        return 3;

                    } else if (releaseDate.getCertification().equalsIgnoreCase("PG")) {

                        return 2;

                    }else if (releaseDate.getCertification().equalsIgnoreCase("G")) {

                        return 1;

                    } else if (releaseDate.getCertification().equalsIgnoreCase("R")) {

                        return 4;

                    } else {

                        return 5;

                    }

                }

            }

        } return 0;

    }

}
