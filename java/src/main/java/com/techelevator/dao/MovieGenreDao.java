package com.techelevator.dao;

import java.util.List;
import java.util.Map;

public interface MovieGenreDao {

    void updateMovieGenreAssociation(List<Integer> genreIds, int movieId);

}
