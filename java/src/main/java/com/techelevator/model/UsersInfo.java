package com.techelevator.model;

import java.sql.Timestamp;
import java.util.Map;

public class UsersInfo {
    //properties
    int user_id;
    String email;
    String full_name;
    Timestamp  birthday;
    String username;
    Map<String, Boolean> favoriteGenres;

    //constructors
    public UsersInfo(int user_id, String email, String full_name, Timestamp birthday, String username, Map<String, Boolean> favoriteGenres) {
        this.user_id = user_id;
        this.email = email;
        this.full_name = full_name;
        this.birthday = birthday;
        this.username = username;
        this.favoriteGenres = favoriteGenres;
    }

    //getters & setter
    public String getUsername() {
        return username;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }


    public String getEmail() {
        return email;
    }

    public String getFull_name() {
        return full_name;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public Map<String, Boolean> getFavoriteGenres() {
        return favoriteGenres;
    }

}
