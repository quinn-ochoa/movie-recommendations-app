package com.techelevator.services;

import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class TMDBService {
    //properties
    private final String API_BASE_URL = "https://api.themoviedb.org/3";
    private final String SEARCH = "/search/movie?query=";
    private final String DISCOVER = "/discover/movie?include_adult=false&include_video=false&language=en-US&page=";
    private int page = 1;
    private RestTemplate restTemplate = new RestTemplate();
    private final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZGU3MzhlZTFmNjUzNGQ3MDFlYjBlZDcwYjBhMDdmNCIsInN1YiI6IjY1ZGJlMjMxZWQyYWMyMDE4NzQwZGQyNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.SrPF0blfo7MklOWQqkeSN8WnfNLQgyUS8r0TtSOdAC4";


    //constructors

    //methods
    public MovieApiResponse getMoviesByTitle(String searchTerm) {


        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        HttpEntity<MovieApiResponse> entity = new HttpEntity<>(headers);
        MovieApiResponse movieApiResponse;
        String formattedSearchTerm = searchTerm.replace(" ", "%20");

        try {

            ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + SEARCH + formattedSearchTerm + "&include_adult=false&page=" + page, HttpMethod.GET, entity, MovieApiResponse.class);
            movieApiResponse = response.getBody();
            //TODO start here
        } catch (RestClientException e) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

        } if (movieApiResponse == null) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No movies found for the given search term.");

        } return movieApiResponse;

    }

    public MovieApiResponse queryForRecommended4u(MovieApiResponse recommended,List<Integer> genres, double vote_average, double vote_count, int neededLayers, int currentLayer) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        HttpEntity<MovieApiResponse> entity = new HttpEntity<>(headers);
        String genresAsApiInput = "";
        genresAsApiInput = createGenresAsApiInput(genresAsApiInput, genres);
        MovieApiResponse layeredResults;
        double voteAverageAdjustmentRatePerLayer = 0.95;
        double voteCountAdjustmentRatePerLayer = 0.9;
        Integer savedGenre;

        if (currentLayer < neededLayers) {

            currentLayer++;
            vote_average = vote_average * voteAverageAdjustmentRatePerLayer;
            vote_count = vote_count * voteCountAdjustmentRatePerLayer;

            for (int i = 0; i < genres.size(); i++) {

                savedGenre = genres.get(i);
                genres.remove(i);
                genresAsApiInput = "";
                genresAsApiInput = createGenresAsApiInput(genresAsApiInput, genres);

                if (currentLayer == neededLayers) {

                    try {

                        ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + DISCOVER + page + "&sort_by=popularity.desc&vote_average.gte=" + vote_average + "&vote_count.gte=" + vote_count + "&with_genres=" + genresAsApiInput, HttpMethod.GET, entity, MovieApiResponse.class);
                        layeredResults = response.getBody();

                        for (Movie movie : layeredResults.getResults()) {

                            if (!recommended.getResults().contains(movie)) {

                                recommended.getResults().add(movie);

                                if (recommended.getResults().size() >= 20) {

                                    break;

                                }

                            }

                        }

                    } catch (RestClientException e) {

                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

                    }

                } else {

                    recommended = queryForRecommended4u(recommended, genres, vote_average, vote_count, neededLayers, currentLayer);

                } genres.add(i, savedGenre);

            }

        } else {

            try {

                ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + DISCOVER + page + "&sort_by=popularity.desc&vote_average.gte=" + vote_average + "&vote_count.gte=" + vote_count + "&with_genres=" + genresAsApiInput, HttpMethod.GET, entity, MovieApiResponse.class);
                recommended = response.getBody();

            } catch (RestClientException e) {

                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

            } while (recommended.getResults().size() < 20) {

                neededLayers++;
                recommended = queryForRecommended4u(recommended, genres, vote_average, vote_count, neededLayers, currentLayer);

            }

        } return recommended;

    }

    private String createGenresAsApiInput(String genresAsApiInput, List<Integer> genres) {

        if (genres.size() > 0) {

            for (Integer genre : genres) {

                if (genresAsApiInput == "") {

                    genresAsApiInput += genre;

                } else {

                    genresAsApiInput += "%2C" + genre;

                }

            }

        } return genresAsApiInput;

    }

    public MovieApiResponse queryForGenreRecommendations(MovieApiResponse recommended, Integer genre, double vote_average, double vote_count, int neededLayers, int currentLayer) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        HttpEntity<MovieApiResponse> entity = new HttpEntity<>(headers);
        MovieApiResponse layeredResults;
        double voteAverageAdjustmentRatePerLayer = 0.95;
        double voteCountAdjustmentRatePerLayer = 0.9;

        if (currentLayer < neededLayers) {

            currentLayer++;

            vote_average = vote_average * voteAverageAdjustmentRatePerLayer;
            vote_count = vote_count * voteCountAdjustmentRatePerLayer;

            if (currentLayer == neededLayers) {

                try {

                    ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + DISCOVER + page + "&sort_by=popularity.desc&vote_average.gte=" + vote_average + "&vote_count.gte=" + vote_count + "&with_genres=" + genre, HttpMethod.GET, entity, MovieApiResponse.class);
                    layeredResults = response.getBody();

                    for (Movie movie : layeredResults.getResults()) {

                        if (!recommended.getResults().contains(movie)) {

                            recommended.getResults().add(movie);

                            if (recommended.getResults().size() >= 20) {

                                break;

                            }

                        }

                    }

                } catch (RestClientException e) {

                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

                }

            } else {

                recommended = queryForGenreRecommendations(recommended, genre, vote_average, vote_count, neededLayers, currentLayer);

            }

        } else {

            try {

                ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + DISCOVER + page + "&sort_by=popularity.desc&vote_average.gte=" + vote_average + "&vote_count.gte=" + vote_count + "&with_genres=" + genre, HttpMethod.GET, entity, MovieApiResponse.class);
                recommended = response.getBody();

            } catch (RestClientException e) {

                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

            } while (recommended.getResults().size() < 20) {

                neededLayers++;
                recommended = queryForGenreRecommendations(recommended, genre, vote_average, vote_count, neededLayers, currentLayer);

            }

        } return recommended;

    }

    public MovieApiResponse queryForPopular() {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        HttpEntity<MovieApiResponse> entity = new HttpEntity<>(headers);
        MovieApiResponse movieApiResponse;


        try {

            ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + DISCOVER + page + "&sort_by=popularity.desc", HttpMethod.GET, entity, MovieApiResponse.class);
            movieApiResponse = response.getBody();

        } catch (RestClientException e) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

        } return movieApiResponse;

    }

    public MovieApiResponse queryForAllTimeGreats(MovieApiResponse recommended ,double vote_average, double vote_count, int neededLayers, int currentLayer) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + BEARER_TOKEN);
        HttpEntity<MovieApiResponse> entity = new HttpEntity<>(headers);
        MovieApiResponse layeredResults;
        double voteAverageAdjustmentRatePerLayer = 0.95;
        double voteCountAdjustmentRatePerLayer = 0.9;

        if (currentLayer < neededLayers) {

            currentLayer++;

            vote_average = vote_average * voteAverageAdjustmentRatePerLayer;
            vote_count = vote_count * voteCountAdjustmentRatePerLayer;

            if (currentLayer == neededLayers) {

                try {

                    ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + DISCOVER + page + "&sort_by=popularity.desc&vote_average.gte=" + vote_average + "&vote_count.gte=" + vote_count, HttpMethod.GET, entity, MovieApiResponse.class);
                    layeredResults = response.getBody();

                    for (Movie movie : layeredResults.getResults()) {

                        if (!recommended.getResults().contains(movie)) {

                            recommended.getResults().add(movie);

                            if (recommended.getResults().size() >= 20) {

                                break;

                            }

                        }

                    }

                } catch (RestClientException e) {

                    throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

                }

            } else {

                recommended = queryForAllTimeGreats(recommended, vote_average, vote_count, neededLayers, currentLayer);

            }

        } else {

            try {

                ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + DISCOVER + page + "&sort_by=popularity.desc&vote_average.gte=" + vote_average + "&vote_count.gte=" + vote_count, HttpMethod.GET, entity, MovieApiResponse.class);
                recommended = response.getBody();

            } catch (RestClientException e) {

                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

            } while (recommended.getResults().size() < 20) {

                neededLayers++;
                recommended = queryForAllTimeGreats(recommended, vote_average, vote_count, neededLayers, currentLayer);

            }

        } return recommended;

    }

}
