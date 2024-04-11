package com.techelevator.services;

import com.techelevator.model.MovieApiResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Component
public class ProfanityFilterService {
    //properties
    private final String API_BASE_URL = "http://localhost:9001/";
    private RestTemplate restTemplate = new RestTemplate();

    //methods
    public MovieApiResponse checkForProfaneTitle(MovieApiResponse movieApiResponse) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MovieApiResponse> entity = new HttpEntity<>(movieApiResponse, headers);

        try {

            ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + "movieApiResponse/", HttpMethod.POST, entity, MovieApiResponse.class);
            movieApiResponse = response.getBody();

        } catch (RestClientException e) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movieAPIResponse object from in house API.", e);

        } return movieApiResponse;

    }

}
