package com.diegoparra.kinoapp.model;

import java.time.LocalDate;
import java.util.List;

public class Movie {

    private final String id;
    private final List<String> genreIds;
    private final String imageUrl;
    private final String title;
    private final float rating;
    private final LocalDate releaseDate;


    public Movie(String id, List<String> genreIds, String imageUrl, String title, Float rating, LocalDate releaseDate) {
        this.id = id;
        this.genreIds = genreIds;
        this.imageUrl = imageUrl;
        this.title = title;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }


    public String getId() {
        return id;
    }

    public List<String> getGenreIds() {
        return genreIds;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public Float getRating() {
        return rating;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
