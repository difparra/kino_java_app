package com.diegoparra.kinoapp.model;

public class People {

    private final String id;
    private final String name;
    private final String character;

    public People(String id, String name, String character) {
        this.id = id;
        this.name = name;
        this.character = character;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCharacter() {
        return character;
    }
}
