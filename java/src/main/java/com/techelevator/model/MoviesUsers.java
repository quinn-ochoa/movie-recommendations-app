package com.techelevator.model;

public class MoviesUsers {
    //properties
    int movie_id;
    int user_id;
    boolean liked;
    String review;

    //constructors
    public MoviesUsers() {
    }

    public MoviesUsers(int movie_id, int user_id, boolean isLiked, String review) {
        this.movie_id = movie_id;
        this.user_id = user_id;
        this.liked = isLiked;
        this.review = review;
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

    public String getReview() {
        return review;
    }

}
