package com.techelevator.dao;

import java.util.List;

public interface MovieGenreDao {

    void updateMovieGenreAssociation(List<Integer> genreIds, int movieId);

}
