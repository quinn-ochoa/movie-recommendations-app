package com.techelevator.model;

public class UsersGenres {
    //properties
    int user_id;
    int genre_id;

    //constructor
    public UsersGenres() {
        }

    public UsersGenres(int user_id, int genre_id){
        this.user_id = user_id;
        this.genre_id = genre_id;
    }

    //getters & setters


    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public int getGenre_id() {
        return genre_id;
    }
}
