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
    boolean nc17_requested;

    //constructors
    public UsersInfo() {
    }
    public UsersInfo(int user_id, String email, String full_name, Timestamp birthday, String username, Map<String, Boolean> favoriteGenres, boolean nc17_requested) {
        this.user_id = user_id;
        this.email = email;
        this.full_name = full_name;
        this.birthday = birthday;
        this.username = username;
        this.favoriteGenres = favoriteGenres;
        this.nc17_requested = nc17_requested;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    public void setFavoriteGenres(Map<String, Boolean> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }

    public boolean isNc17_requested() {
        return nc17_requested;
    }

    public void setNc17_requested(boolean nc17_requested) {
        this.nc17_requested = nc17_requested;
    }

}
