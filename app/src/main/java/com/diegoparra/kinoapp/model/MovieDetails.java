package com.diegoparra.kinoapp.model;

import java.time.LocalDate;
import java.util.List;

public class MovieDetails {

    private final String id;
    private final List<Genre> genres;
    private final String originalLanguage;
    private final String originalTitle;
    private final String imageUrl;
    private final String overview;
    private final LocalDate releaseDate;
    private final int runtime;
    private final float rating;
    private final People director;
    private final List<People> cast;
    private final List<Movie> suggestions;
    private final List<Review> reviews;


    public MovieDetails(String id, List<Genre> genres, String originalLanguage, String originalTitle, String imageUrl, String overview, LocalDate releaseDate, int runtime, float rating, People director, List<People> cast, List<Movie> suggestions, List<Review> reviews) {
        this.id = id;
        this.genres = genres;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.imageUrl = imageUrl;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.rating = rating;
        this.director = director;
        this.cast = cast;
        this.suggestions = suggestions;
        this.reviews = reviews;
    }

    public String getId() {
        return id;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getOverview() {
        return overview;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public float getRating() {
        return rating;
    }

    public People getDirector() {
        return director;
    }

    public List<People> getCast() {
        return cast;
    }

    public List<Movie> getSuggestions() {
        return suggestions;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
