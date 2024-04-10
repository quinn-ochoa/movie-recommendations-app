package org.example.controller;

import org.example.models.Movie;
import org.example.models.MovieApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
@CrossOrigin
public class ProfanityController {
    
    private MovieApiResponse movieApiResponse;

    @RequestMapping(path = "/movieApiResponse/", method = RequestMethod.POST)

    public MovieApiResponse addCleanTitles(@RequestBody MovieApiResponse movieApiResponse) {

        movieApiResponse.setResults(checkForProfanity(movieApiResponse.getResults()));

        return movieApiResponse;

    }

    public Boolean containsProfanity(String title) {

        title = title.toLowerCase();

        try(Scanner reader = new Scanner(new File("src/main/resources/badWords.txt"))){

            while(reader.hasNextLine()){

                String line = reader.nextLine().toLowerCase();
                String exactWord = " "+ line + " ";

                if(title.contains(exactWord)||title.startsWith(line)|| title.endsWith(line)){

                    return true;

                }

            }

        } catch (FileNotFoundException e) {

            throw new RuntimeException(e);

        } return false;

    }
    public List<Movie> checkForProfanity(List<Movie> unfiltered){

        List<Movie> filtered = new ArrayList<>();

        for (Movie movie : unfiltered){

            if(!containsProfanity(movie.getTitle())){

                filtered.add(movie);

            }

        } return filtered;

    }

}
