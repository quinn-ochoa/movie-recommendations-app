package com.techelevator.model;

public class Genres {
    //properties
    int id;
    String name;

    //constructors
    public Genres() {
        }

    public Genres(int id, String name) {
    }

    //getters & setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
