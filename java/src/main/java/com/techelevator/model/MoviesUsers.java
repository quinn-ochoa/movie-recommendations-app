package com.techelevator.model;

public class MoviesUsers {
    //properties
    int movie_id;
    int user_id;
    boolean liked;
    String review;
    int review_id;

    //constructors
    public MoviesUsers() {
    }

    public MoviesUsers(int movie_id, int user_id, boolean isLiked, String review, int review_id) {
        this.movie_id = movie_id;
        this.user_id = user_id;
        this.liked = isLiked;
        this.review = review;
        this.review_id = review_id;
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

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public void setReview(String review) {
        this.review = review;
    }

}
