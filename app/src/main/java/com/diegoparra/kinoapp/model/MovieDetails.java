package com.diegoparra.kinoapp.model;

import com.diegoparra.kinoapp.utils.ListUtils;

import java.time.LocalDate;
import java.util.List;

public class MovieDetails {

    private final String id;
    private final List<Genre> genres;
    private final String language;
    private final String title;
    private final String posterUrl;
    private final String backdropUrl;
    private final String overview;
    private final LocalDate releaseDate;
    private final int runtime;          //  Runtime in minutes
    private final float rating;         //  Rating over 10.0f
    private final People director;
    private final List<People> cast;
    private final List<Movie> suggestions;
    private final List<Review> reviews;


    public MovieDetails(String id, List<Genre> genres, String language, String title, String posterUrl, String backdropUrl, String overview, LocalDate releaseDate, int runtime, float rating, People director, List<People> cast, List<Movie> suggestions, List<Review> reviews) {
        this.id = id;
        this.genres = genres;
        this.language = language;
        this.title = title;
        this.posterUrl = posterUrl;
        this.backdropUrl = backdropUrl;
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

    public String getLanguage() {
        return language;
    }

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getBackdropUrl() {
        return backdropUrl;
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


    public Movie toMovie() {
        return new Movie(
                id,
                ListUtils.map(genres, Genre::getId),
                posterUrl,
                title,
                rating,
                releaseDate
        );
    }
}
