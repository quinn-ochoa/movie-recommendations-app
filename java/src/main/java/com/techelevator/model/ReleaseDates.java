package com.techelevator.model;

public class ReleaseDates {
    //properties
    String certification;

    //constructors
    public ReleaseDates() {
    }
    public ReleaseDates(String certification) {
        this.certification = certification;
    }

    //methods
    public String getCertification() {
        return certification;
    }
}
