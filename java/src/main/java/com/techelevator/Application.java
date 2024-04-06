package com.techelevator;

import com.techelevator.model.Movie;
import com.techelevator.model.MovieApiResponse;
import com.techelevator.services.TMDBService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
       
    }

}
