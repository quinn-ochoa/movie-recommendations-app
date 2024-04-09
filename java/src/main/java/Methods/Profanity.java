package Methods;

import com.techelevator.model.Movie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Profanity {
   // public String wordPath = "path/to/badWords.txt";


    public Boolean containsProfanity(String title) {
        title = title.toLowerCase();
        try(Scanner reader = new Scanner(new File("path/to/badWords.txt"))){
            while(reader.hasNextLine()){
                String line = reader.nextLine().toLowerCase();
                  String exactWord = " "+ line + " ";
                if(title.contains(exactWord)||title.startsWith(line)|| title.endsWith(line)){
                    return true;
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public List<Movie> checkForProfanity(List<Movie> unfiltered){
        for (Movie movie : unfiltered){
            if(containsProfanity(movie.getTitle())){
                unfiltered.remove(movie);
            }
        }
        return unfiltered;
    }


}
