package com.techelevator.services;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class TMDBService {
    //properties
    private final String API_BASE_URL = "https://api.themoviedb.org/3/search/movie?query=";
    private RestTemplate restTemplate = new RestTemplate();
    private final String bearerToken = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmZGU3MzhlZTFmNjUzNGQ3MDFlYjBlZDcwYjBhMDdmNCIsInN1YiI6IjY1ZGJlMjMxZWQyYWMyMDE4NzQwZGQyNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.SrPF0blfo7MklOWQqkeSN8WnfNLQgyUS8r0TtSOdAC4";

    //methods
    public MovieApiResponse getMoviesByTitle(String searchTerm) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + bearerToken);
        HttpEntity<MovieApiResponse> entity = new HttpEntity<>(headers);
        MovieApiResponse movieApiResponse;
        String formattedSearchTerm = searchTerm.replace(' ', '%');

        try {

            ResponseEntity<MovieApiResponse> response = restTemplate.exchange(API_BASE_URL + formattedSearchTerm + "&page=1", HttpMethod.GET, entity, MovieApiResponse.class);
            movieApiResponse = response.getBody();

        } catch (RestClientException e) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error retrieving movie from API using search term.", e);

        } if (movieApiResponse == null) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No movies found for the given search term.");

        } movieApiResponse = addMovieNameToResponse(movieApiResponse);

        return movieApiResponse;

    }

    private MovieApiResponse addMovieNameToResponse(MovieApiResponse movieApiResponse) {
        //TODO Start Here
        String sql = "SELECT name FROM genres WHERE id = ?;";
        String currentSelectedGenreName;
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        for (Movie movie : movieApiResponse.getResults()) {

            for (Integer genre_id : movie.getGenre_ids()) {

                try{

                    currentSelectedGenreName = jdbcTemplate.queryForObject(sql, String.class, genre_id);
                    movie.getGenre_names().add(currentSelectedGenreName);

                } catch (CannotGetJdbcConnectionException e){

                    throw new DaoException("Unable to connect to server or database", e);

                } catch (DataIntegrityViolationException e){

                    throw new DaoException("Data integrity violation", e);

                }

            }

        } return null;

    }

}
