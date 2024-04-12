package com.techelevator.dao;

public interface MovieCertificationDao {

    void updateMovieCertificationAssociation(int rating, int movie_id);

    int getCertificationByMovieId(int movie_id);
}
