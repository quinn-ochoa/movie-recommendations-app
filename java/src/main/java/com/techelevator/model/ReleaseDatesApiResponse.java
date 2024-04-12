package com.techelevator.model;

import java.util.List;

public class ReleaseDatesApiResponse {
    //properties
    List<CountryReleaseDates> results;

    //constructors
    public ReleaseDatesApiResponse() {
    }
    public ReleaseDatesApiResponse(List<CountryReleaseDates> results) {
        this.results = results;
    }

    //getters & setters
    public List<CountryReleaseDates> getResults() {
        return results;
    }

}
