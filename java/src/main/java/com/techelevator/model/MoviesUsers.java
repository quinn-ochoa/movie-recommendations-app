package com.techelevator.model;

public class MoviesUsers {
    //properties
    int movie_id;
    int user_id;
    boolean isLiked;

    //constructors
    public MoviesUsers() {
    }

    public MoviesUsers(int movie_id, int user_id, boolean isLiked) {
        this.movie_id = movie_id;
        this.user_id = user_id;
        this.isLiked = isLiked;
    }

    //getters & setters
}
