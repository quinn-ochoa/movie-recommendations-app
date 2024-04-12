package com.techelevator.services;

import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Component
public class ProfanityFilterAPIService {
    //properties
    private final String API_BASE_URL = "http://localhost:9001/";
    private RestTemplate restTemplate = new RestTemplate();

    //methods
    public Movie checkForProfaneTitle(Movie movie) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Movie> entity = new HttpEntity<>(movie, headers);

        try {

            ResponseEntity<Movie> response = restTemplate.exchange(API_BASE_URL + "movieApiResponse/", HttpMethod.POST, entity, Movie.class);
            movie = response.getBody();

        } catch (RestClientException e) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movieAPIResponse object from in house API.", e);

        } return movie;

    }

}
