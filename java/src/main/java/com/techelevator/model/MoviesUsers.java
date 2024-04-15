package com.techelevator.model;

public class MoviesUsers {
    //properties
    int movie_id;
    int user_id;
    boolean liked;

    //constructors
    public MoviesUsers() {
    }

    public MoviesUsers(int movie_id, int user_id, boolean isLiked) {
        this.movie_id = movie_id;
        this.user_id = user_id;
        this.liked = isLiked;
    }

    //getters & setters

    public int getMovie_id() {
        return movie_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public boolean isLiked() {
        return liked;
    }
}
