package com.techelevator.model;

import java.util.List;

public class CountryReleaseDates {
    //properties
    String iso_3166_1;
    List<ReleaseDates> release_dates;

    //constructors
    public CountryReleaseDates() {
    }
    public CountryReleaseDates(String iso_3166_1 ,List<ReleaseDates> release_dates) {
        this.iso_3166_1 = iso_3166_1;
        this.release_dates = release_dates;
    }

    //getters & setters
    public String getIso_3166_1() {
        return iso_3166_1;
    }

    public List<ReleaseDates> getRelease_dates() {
        return release_dates;
    }

}
